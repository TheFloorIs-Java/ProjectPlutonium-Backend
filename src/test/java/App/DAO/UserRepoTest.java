package App.DAO;

import App.App;
import App.Controller.UserController;
import App.Models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepoTest {



        @Autowired
        public UserRepository ur;


        @Test
        public void addUser()
        {
            User user = User.builder()
                    .username("laalsanedo")
                    .password("2525")
                    .permission_level(0)
                    .salt("esfbsdfvsdf")
                    .build();

            ur.save(user);
        }

        @Test
        public void deleteUser(){
            User user = ur.findById(7).get();

            ur.delete(user);

        }

        @Test
    public void getUserByID(){
            User user = ur.findById(3).get();
            System.out.println(user.getUsername());
        }

        @Test
        public void updateUserPermissionLevel(){
            User existingUser = ur.findById(2).get();
            existingUser.setPermission_level(0);
            ur.save(existingUser);

        }



}