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

        Session session = sessionRepo.findSessionBySession_id(sessionId);

        if (session == null){
            System.out.println("Session not found for user.");
            return null;
        }
        session.getUser().setPassword(null);
        session.getUser().setSalt(null);
        return session;
    }

    public Session newSession(User user){
        Date date = new Date();
        date.setTime(date.getTime()+84000000);
        Optional<Session> optionalSession = sessionRepo.findSessionByUser(user);
        if (optionalSession.isPresent()){
            Session session = optionalSession.get();
            session.setSession_expiry(date);
            session.setSession_id(SessionIDUtil.getSessionID().toString());
            session = sessionRepo.save(session);
            session.getUser().setPassword(null);
            session.getUser().setSalt(null);
            return session;

        }
        Session session = Session.builder()
                .session_expiry(date)
                .session_id(SessionIDUtil.getSessionID().toString())
                .user(user)
                .build();
        session = sessionRepo.save(session);
        session.getUser().setPassword(null);
        session.getUser().setSalt(null);
        return session;
    }

    public void deleteSession(String session){

        sessionRepo.deleteSessionBySession_id(session);
    }

    public List<Session> getAllSessions() {
        return sessionRepo.findAll();
    }
}
