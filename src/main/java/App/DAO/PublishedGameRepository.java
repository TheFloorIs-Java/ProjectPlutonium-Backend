package App.DAO;

import App.Models.DailyChallenge;
import App.Models.PublishedGame;
import App.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PublishedGameRepository extends JpaRepository<PublishedGame, Integer> {

    List<PublishedGame> findPublishedGameByUser(User user);



}
