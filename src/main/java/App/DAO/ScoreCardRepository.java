package App.DAO;

import App.Models.ScoreCard;
import App.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScoreCardRepository extends JpaRepository<ScoreCard, Integer> {

    List<ScoreCard> findScoreCardByUser(User user);

}
