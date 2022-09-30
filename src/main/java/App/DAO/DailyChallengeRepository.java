package App.DAO;

import App.Models.DailyChallenge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyChallengeRepository extends JpaRepository<DailyChallenge,Integer> {
}
