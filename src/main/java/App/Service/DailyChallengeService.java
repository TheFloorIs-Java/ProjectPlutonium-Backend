package App.Service;

import App.DAO.DailyChallengeRepository;
import App.DAO.PublishedGameRepository;
import App.Models.DailyChallenge;
import App.Models.PublishedGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class DailyChallengeService {
    @Autowired
    DailyChallengeRepository dcr;



    public DailyChallenge getDailyChallengeByDate(Date date) {
        if(dcr.findDailyChallengeByChallengeDate(date).isPresent()){
            return dcr.findDailyChallengeByChallengeDate(date).get();
        }
           return null;
    }

    public List<DailyChallenge> getAllDailyChallenges(){
        return dcr.findAll();
    }

    public DailyChallenge addDailyChallenge(DailyChallenge dc) {
        return dcr.save(dc);

    }

    public List<DailyChallenge> removeDailyChallengeById(int id) {
         dcr.deleteById(id);
        return dcr.findAll();
    }


}
