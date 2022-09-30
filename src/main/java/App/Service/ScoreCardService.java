package App.Service;


import App.DAO.ScoreCardRepository;
import App.Models.PublishedGame;
import App.Models.ScoreCard;
import App.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class ScoreCardService {

    ScoreCardRepository scr;
    @Autowired
    public ScoreCardService(ScoreCardRepository scr) {
        this.scr = scr;
    }

    public List<ScoreCard> getScoreCardByPlayerId(int id){
        User user = User.builder()
                .user_id(id)
                .build();
        return scr.findScoreCardByUser(user);
    }

    public ScoreCard addScoreByPlayerId() {
        return null;
    }

    public ScoreCard addScoreCard(int score, int user_id, int published_game_id) {
        User user = User.builder().user_id(user_id).build();
        PublishedGame publishedGame = PublishedGame.builder().game_id(published_game_id).build();

        Date date = new Date();
        date.getTime();
        ScoreCard scoreCard = ScoreCard.builder()
                .published_game(publishedGame)
                .score(score)
                .user(user)
                .date_submitted(date)
                .build();
       return scr.save(scoreCard);
    }
}
