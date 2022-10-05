package App.DAO;

import App.Models.PublishedGame;
import App.Models.ScoreCard;
import App.Models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ScoreCardRepositoryTest {

    @Autowired
    ScoreCardRepository scr;

    @Test
    void saveScore(){

        User user = User.builder()
                .user_id(1)
                .build();

        PublishedGame publishedGame = PublishedGame.builder()
                .game_id(1)
                .build();

        Date date = new Date();
        date.getTime();

        ScoreCard scoreCard = ScoreCard.builder()
                .user(user)
                .publishedGame(publishedGame)
                .date_submitted(date)
                .score(100)
                .build();

        scr.save(scoreCard);
    }

    @Test
    void getScoreCardData(){
        User user = User.builder()
                .user_id(1)
                .build();

        List<ScoreCard> scoreCard = scr.findScoreCardByUser(user);

    }

    @Test
    void getScoreCardByUserAndPublishedGame(){
        User user = User.builder()
                .user_id(10)
                .build();
        PublishedGame publishedGame = PublishedGame.builder().game_id(29).build();

        System.out.println(scr.findScoreCardByUserAndPublishedGame(user, publishedGame).toString());
    }
}