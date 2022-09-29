package App.DAO;

import App.Models.Session;
import App.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository  extends JpaRepository<Session, Integer> {

    @Modifying
    @Transactional
    @Query(
            value = "delete sessions where session_id = ?1",
            nativeQuery = true
    )
    void deleteSessionBySession_id(String session);

    Optional<Session> findSessionByUser(User user);

    @Query(
            value = "select user_info.user_id, username, permission_level, session_id, session_expiry from sessions join user_info on sessions.user_id = user_info.user_id " +
                    "where session_id = ?1",
            nativeQuery = true
    )
    Session findSessionBySession_id(String sessionId);

}
