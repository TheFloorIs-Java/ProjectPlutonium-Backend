package App.Service;


import App.DAO.PublishedGameRepository;
import App.DAO.ScoreCardRepository;
import App.DAO.UserRepository;
import App.Models.PublishedGame;
import App.Models.ScoreCard;
import App.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ScoreCardService {

    ScoreCardRepository scr;

    //:(
    UserRepository ur;
    PublishedGameRepository pgr;

    @Autowired
    public ScoreCardService(ScoreCardRepository scr, UserRepository ur, PublishedGameRepository pgr) {
        this.scr = scr;
        this.ur = ur;
        this.pgr = pgr;
    }

    public List<ScoreCard> getScoreCardByPlayerId(int id){
        User user = ur.findById(id).get();
        return scr.findScoreCardByUser(user);
    }

    public ScoreCard addScoreByPlayerId() {
        return null;
    }

    public ScoreCard addScoreCard(ScoreCard scoreCard) {
        User user = ur.findById(scoreCard.getUser().getUser_id()).get();
        PublishedGame publishedGame = pgr.findById(scoreCard.getPublishedGame().getGame_id()).get();

        publishedGame.setNumber_of_plays(publishedGame.getNumber_of_plays()+1);
        pgr.save(publishedGame);

        List<ScoreCard> ExistingScoreCard = scr.findScoreCardByUserAndPublishedGame(user,publishedGame);
        if (ExistingScoreCard.size() > 0) {
            int score = scoreCard.getScore();
            scoreCard = ExistingScoreCard.get(0);
            if (scoreCard.getScore() < score)
                scoreCard.setScore(score);
        }
        else {
            scoreCard.setUser(user);
            scoreCard.setPublishedGame(publishedGame);
        }


        return scr.save(scoreCard);
    }

    public List<ScoreCard> getAllScoreCards() {
        return scr.findAll();
    }

    public List<ScoreCard> getAggregateScores(){
        List<ScoreCard> scoreCards = new ArrayList<>();
        List<Integer> userIds = scr.findDistinctUserId();

        for (int userId: userIds){
            User user = ur.findById(userId).get();
            ScoreCard scoreCard = ScoreCard.builder()
                    .user(user)
                    .score(scr.getSumOfUserScore(userId))
                    .build();
            scoreCards.add(scoreCard);
        }


        return scoreCards;
    }
}
