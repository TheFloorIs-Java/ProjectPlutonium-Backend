package App.DAO;

import App.Models.DailyChallenge;
import App.Models.PublishedGame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface DailyChallengeRepository extends JpaRepository<DailyChallenge,Integer> {

    Optional<DailyChallenge> findDailyChallengeByChallengeDate(Date date);

    List<DailyChallenge> findDailyChallengeByPublishedGame(PublishedGame publishedGame);

}
