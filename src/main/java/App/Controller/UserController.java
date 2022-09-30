package App.Controller;

import App.Models.*;
import App.Service.SessionService;
import App.Service.SpringTestService;
import App.Service.UserService;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    private final SpringTestService sts;
    private final UserService us;

    private final SessionService ss;

    @Autowired
    public UserController(SpringTestService sts, UserService us, SessionService ss){
        this.sts = sts;
        this.us = us;
        this.ss = ss;
    }


    @GetMapping("/users/id/{id}")
    public User getUserById(@PathVariable("id") int id){
        User user = us.getUserById(id);
        return user;
    }

    @GetMapping("/users/session")
    public User getUserBySession(@RequestHeader Map<String, String> headers){
        Session session = null;
        if (headers.get("session") != null) {
            System.out.println(headers.get("session"));
            session = ss.getSessionInfo(headers.get("session"));
        }
        return session.getUser();
    }

    @GetMapping("/users/all")
    public List<User> getAllUsers(){
        List<User> users = us.getAllUsers();
        return users;
    }

    @GetMapping("/login")
    public ResponseEntity<Session> attemptLogin(@RequestBody User user){
        User loggedInUser = us.AttemptLogin(user);
        Session session = null;
        if (loggedInUser != null){
            session = ss.newSession(loggedInUser);
            return new ResponseEntity<>(session, HttpStatus.OK);
        }
        return null;
    }

    @PostMapping("/users")
    public Session registerUsers(@RequestBody User user){
        User registeredInUser = us.AttemptRegister(user);
        Session session = null;
        if (registeredInUser != null){
            session = ss.newSession(registeredInUser);
            return session;
        }
        return null;
    }

    @PutMapping("/users/id/{id}")
    public User updateUserById(@PathVariable("id") int id, @RequestBody User user){
        User updatedUser = us.updateUserById(user, id);
        return updatedUser;
    }

    @GetMapping("/sessions/all")
    public List<Session> getAllSessions(){
        List<Session> sessions = ss.getAllSessions();
        return sessions;
    }
}