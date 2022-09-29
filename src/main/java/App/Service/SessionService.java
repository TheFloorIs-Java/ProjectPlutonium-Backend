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
import java.util.List;
import java.util.Optional;

@Component
public class SessionService {


    @Autowired
    private SessionRepository sessionRepo;
    public Session getSessionInfo(User user){

        Optional<Session> session =  sessionRepo.findSessionByUser(user);
        if (session.isPresent()){
            Session session1 = session.get();
            session1.getUser().setPassword(null);
            session1.getUser().setSalt(null);
            return session1;
        }
        else {
            return null;
        }
    }

    public Session getSessionInfo(String sessionId){

        Session session =  sessionRepo.findSessionBySession_id(sessionId);
        session.getUser().setPassword(null);
        session.getUser().setSalt(null);
        return session;
    }


    public Session newSession(User user){

        Date date = new Date();
        date.setTime(date.getTime()+86400000);

        Session session = new Session(user, SessionIDUtil.getSessionID().toString(), date);
        session.getUser().setSalt(null);
        session.getUser().setPassword(null);
        return sessionRepo.save(session);
    }

    public void deleteSession(String session){

        sessionRepo.deleteSessionBySession_id(session);
    }

}
