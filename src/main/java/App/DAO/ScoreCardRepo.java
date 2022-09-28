package App.DAO;

import App.Models.ScoreCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreCardRepo extends JpaRepository<ScoreCard, Integer> {
}
