package App.Controller;

import App.Models.*;
import App.Service.SpringTestService;
import App.Service.UserServiceNew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    private final SpringTestService sts;
    private final UserServiceNew us;

    @Autowired
    public UserController(SpringTestService sts, UserServiceNew us){
        this.sts = sts;
        this.us = us;
    }


    @GetMapping("/users/id/{id}")
    public User getUserById(@PathVariable("id") int id){
        User user = us.getUserById(id);
        return user;
    }

    @GetMapping("/users/session")
    public User getUserBySession(@RequestHeader Map<String, String> headers){
        User user = null;
        if (headers.get("session") != null)
            user = us.getUserBySession(headers.get("session"));
        return user;
    }

    @GetMapping("/users/all")
    public List<User> getAllUsers(){
        List<User> users = us.getAllUsers();
        return users;
    }

    @GetMapping("/login")
    public User attemptLogin(@RequestBody User user){
        User loggedInUser = us.AttemptLogin(user);
        return loggedInUser;
    }

    @PostMapping("/users")
    public User registerUsers(@RequestBody User user){
        User registeredInUser = us.AttemptRegister(user);
        return registeredInUser;
    }

    @PutMapping("/users/id/{id}")
    public User updateUserById(@PathVariable("id") int id, @RequestBody User user){
        User updatedUser = us.updateUserById(id);
        return updatedUser;
    }
}