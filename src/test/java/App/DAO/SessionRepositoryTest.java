package App.DAO;

import App.Models.Session;
import App.Models.User;
import App.Service.SessionService;
import App.Utility.SessionIDUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
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
        String session = "0e063c65-b6e6-4c4b-974c-bb5b26bad92b";
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


    @Test
    void updateSessionID(){
        User user = User.builder()
                .user_id(1)
                .build();
        Session session = sessionRepo.findSessionByUser(user).get();
        session.setSession_id(SessionIDUtil.getSessionID().toString());
        Date date = new Date();
        date.setTime(date.getTime()+84600000);
        session.setSession_expiry(date);
        sessionRepo.save(session);
        System.out.println(session);
    }


    @Test
    void testingNewSession(){
        User user = User.builder().user_id(1).build();
        System.out.println(sessionSer.newSession(user));

    }





}