package App.Service;

import App.DAO.SessionRepository;
import App.Models.Session;

import App.Models.User;
import App.Utility.SessionIDUtil;
import org.apache.catalina.manager.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.persistence.Entity;
import java.util.Date;

@Component
public class SessionService {

    @Autowired
    SessionRepository sessionRepo;
    public Session getSessionInfo(int userId){
        return sessionRepo.getReferenceById(userId);
    }

    public Session newSession(User user){

        Date date = new Date();
        date.setTime(date.getTime()+86400000);

        Session session = Session.builder()
                .session_id(SessionIDUtil.getSessionID().toString())
                .session_expiry(date)
                .user(user)
                .build();

        return sessionRepo.save(session);
    }

    public void deleteSession(String session){

        sessionRepo.deleteSessionBySession_id(session);
    }

}
