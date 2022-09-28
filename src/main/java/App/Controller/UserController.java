package App.Controller;

import App.Models.*;
import App.Service.SpringTestService;
import App.Service.UserServiceNew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

        User user = us.getUserInfo(id);

        return new User();
    }
}