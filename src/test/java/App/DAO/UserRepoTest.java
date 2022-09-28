package App.DAO;

import App.App;
import App.Controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = App.class)
class UserRepoTest {



        @Autowired
        public UserController uc;
        @Autowired
        public UserRepo ur;


        @Test
        public void testGetAllUsers()
        {
            assertTrue(this.uc.getAllUsers().size() != 0);
        }

        @Test
        public void getUsernameById()
        {
            assertEquals("Matt", ur.getReferenceById(1).getUsername());
        }



}