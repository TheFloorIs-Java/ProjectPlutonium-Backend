package App.Service;

import App.DAO.UserRepo;
import App.Models.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceNew {

    UserRepo userRepo;

    public UserServiceNew(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    public User getUserById(int id) {
        return userRepo.findById(id).get();
    }

    public User getUserBySession(String session) {
        return null;
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User AttemptLogin(User user) {
        return null;
    }

    public User AttemptRegister(User user) {
        return userRepo.save(user);
    }

    public User updateUserById(User user, int id) {
        //will put a condition to check if the user exists in the database but for now you can update.
        return null;
    }
}
