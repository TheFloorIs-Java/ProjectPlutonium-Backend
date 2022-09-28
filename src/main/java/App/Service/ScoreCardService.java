package App.Service;


import App.DAO.ScoreCardRepository;
import App.Models.ScoreCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScoreCardService {

    ScoreCardRepository scr;
    @Autowired
    public ScoreCardService(ScoreCardRepository scr) {
        this.scr = scr;
    }

    public ScoreCard getScoreCardByPlayerId(){
        return null;
    }

    public ScoreCard addScoreByPlayerId() {
        return null;
    }

    public void addScoreCard(ScoreCard scoreCard) {
    }
}
