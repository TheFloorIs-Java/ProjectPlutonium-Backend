package App.DAO;

import App.Models.PublishedGame;
import App.Models.ScoreCard;
import App.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ScoreCardRepository extends JpaRepository<ScoreCard, Integer> {


    List<ScoreCard> findScoreCardByUser(User user);

    List<ScoreCard> findScoreCardByUserAndPublishedGame(User user, PublishedGame publishedGame);

    @Query(
            nativeQuery = true,
            value = "SELECT sum(score) from user_scores WHERE user_id = ?1"
    )
    int getSumOfUserScore(int user_id);

    @Query(
            nativeQuery = true,
            value = "SELECT distinct(user_id) from user_scores"
    )
    List<Integer> findDistinctUserId();
}
