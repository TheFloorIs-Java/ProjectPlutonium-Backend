package App.DAO;

import App.Models.Session;
import App.Models.User;
import App.Service.SessionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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

    @Test
    void getSession(){
        User user = User.builder()
                .user_id(1)
                .build();
        Session session = sessionSer.getSessionInfo(user);
        session.getUser().setPassword(null);
        session.getUser().setSalt(null);
        System.out.println(session);
    }

    @Test
    void getSession1(){
        String sess = "afe073bb-52d5-4a1a-be5e-825697f8196e";
        Session session = sessionSer.getSessionInfo(sess);
        System.out.println(session);
    }



}