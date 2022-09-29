package App.DAO;

import App.Models.Session;
import App.Models.User;
import App.Service.SessionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SessionRepositoryTest {

    @Autowired
    SessionRepository sessionRepo;
    @Autowired
    SessionService sessionSer;

    @Test
    void setSession(){
        User user = User.builder()
                .user_id(1)
                .build();
        Session session = sessionSer.newSession(user);
        System.out.println(session);
    }

    @Test
    void deleteSession(){
        String session = "c5e3cc04-d0f8-4061-bdb5-125343e05042";
        sessionSer.deleteSession(session);
    }




}