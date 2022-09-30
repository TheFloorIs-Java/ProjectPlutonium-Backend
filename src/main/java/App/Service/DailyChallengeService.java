package App.Service;

import App.DAO.DailyChallengeRepository;
import App.Models.DailyChallenge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DailyChallengeService {
    @Autowired
    DailyChallengeRepository dcr;
    public Optional<DailyChallenge> getDailyChallengeByDate(int date) {
        return dcr.findById(date);
    }
}
