package App.DAO;

import App.App;
import App.Controller.UserController;
import App.Models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class UserRepoandControllerTest {




        @Autowired
        public UserRepository ur;
        @Autowired
        public UserController uc;


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
        public void updateUserPermissionLevel() {
            User existingUser = ur.findById(2).get();
            existingUser.setPermission_level(0);
            ur.save(existingUser);
        }

        @Test
        public void getUsernameById()
        {
        assertEquals("Matt", uc.getUserById(1).getUsername());
        }



}