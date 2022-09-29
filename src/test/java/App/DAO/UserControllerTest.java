package App.DAO;

import App.App;
import App.Controller.UserController;
import App.Models.User;
import App.Service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class UserRepoServiceandControllerTest {




        @Autowired
        public UserRepository ur;
        @Autowired
        public UserController uc;

        @Autowired
        public UserService us;


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
        void tryUserLogin(){
            User user = new User();
            user.setUsername("laalsanedo");
            user.setPassword("2525");
            us.AttemptLogin(user);
        }

        @Test
        void tryUserRegister(){ //Can be null
            User user = new User();
            user.setUsername("laalsanedo");
            user.setPassword("2525");
            user.setProfile_pic_url("https://cdn-1.motorsport.com/images/mgl/63vVbllY/s1200/max-verstappen-red-bull-racing-1.webp");
            us.AttemptRegister(user);
        }

        @Test
        public void getUsernameById()
        {
        assertEquals("Matt", uc.getUserById(1).getUsername());
        }



}