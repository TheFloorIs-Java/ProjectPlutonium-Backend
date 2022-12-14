package App.Service;

import App.DAO.PublishedGameRepository;
import App.DAO.ScoreCardRepository;
import App.DAO.SessionRepository;
import App.DAO.UserRepository;
import App.Models.ScoreCard;
import App.Models.Session;
import App.Models.User;
import App.Utility.HashNSaltUtil;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Component

public class UserService {


    UserRepository userRepo;

    // :(
    PublishedGameRepository ps;
    ScoreCardRepository scs;
    SessionRepository ss;

    public UserService(UserRepository userRepo, PublishedGameRepository ps, ScoreCardRepository scs, SessionRepository ss){
        this.userRepo = userRepo;
        this.ps = ps;
        this.scs = scs;
        this.ss = ss;
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
        User previousUser = userRepo.findById(id).get();
        previousUser.setUsername(user.getUsername());
        previousUser.setProfile_pic_url(user.getProfile_pic_url());
        previousUser.setPermission_level(user.getPermission_level());
        userRepo.save(previousUser);
        return previousUser;
    }

    public User deleteUserById(int id) {
        User user = userRepo.findById(id).get();
        ps.deleteAll(ps.findPublishedGameByUser(user));
        scs.deleteAll(scs.findScoreCardByUser(user));
        ss.delete(ss.findSessionByUser(user).get());
        userRepo.delete(user);
        return user;
    }

    public User updateUserProfilePicUrl(User user) {
        User user1 = userRepo.findById(user.getUser_id()).get();
        user1.setProfile_pic_url(user.getProfile_pic_url());
        userRepo.save(user1);
        return user1;
    }
}
