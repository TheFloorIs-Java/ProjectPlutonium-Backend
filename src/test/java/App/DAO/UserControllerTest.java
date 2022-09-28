package App.DAO;

import App.App;
import App.Controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = App.class)
class UserControllerTest {



        @Autowired
        public UserController uc;


        @Test
        public void testGetAllUsers()
        {
            assertTrue(this.uc.getAllUsers().size() != 0);
        }

        @Test
        public void getUsernameById()
        {
            assertEquals("Matt", uc.getUserById(1).getUsername());
        }



}