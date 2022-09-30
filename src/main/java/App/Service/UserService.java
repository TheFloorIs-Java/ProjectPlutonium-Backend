package App.Service;

import App.DAO.UserRepository;
import App.Models.Session;
import App.Models.User;
import App.Utility.HashNSaltUtil;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {


    UserRepository userRepo;

    public UserService(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    public User getUserById(int id) {
        return userRepo.findById(id).get();
    }

    public List<User> getAllUsers() {
        List<User> users = userRepo.findAll();
        for (int i = 0; i < users.size(); i++){
            users.get(i).setPassword(null);
            users.get(i).setSalt(null);
        }
        return users;
    }

    public User AttemptLogin(User user) {
        //encrypting the password
        String username = user.getUsername();
        user.setSalt(userRepo.findUserByUsername(username).getSalt());
        user.setPassword(HashNSaltUtil.saltAndHash(user.getPassword(), user.getSalt()));
        user = userRepo.findUserByUsernameAndPasswordAndSalt(user.getUsername(), user.getPassword(), user.getSalt());
        if (user!=null){
            return user;
        }
        else {
            return null;
        }
    }

    public User AttemptRegister(User user) {
        user.setSalt(HashNSaltUtil.generateSalt());
        user.setPassword(HashNSaltUtil.saltAndHash(user.getPassword(), user.getSalt()));
        try{
            return userRepo.save(user);
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public User updateUserById(User user, int id) {
        //will put a condition to check if the user exists in the database but for now you can update.
        return null;
    }

}
