package App.Service;

import App.DAO.PublishedGameRepository;
import App.DAO.ScoreCardRepository;
import App.Models.DailyChallenge;
import App.Models.PublishedGame;
import App.Models.ScoreCard;
import App.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Component
public class PublishedGameService {
    @Autowired
    PublishedGameRepository pgr;
    @Autowired
    ScoreCardRepository scr;

    public void addPublishedGame(PublishedGame pg) {
        pgr.save(pg);
    }
    public Optional<PublishedGame> getPublishedGameById(int id) {
        return pgr.findById(id);
    }

    public List<PublishedGame> getPublishedGamesById(int id) {
        User user = new User();
        user.setUser_id(id);
        return pgr.findPublishedGameByUser(user);
    }

    public List<PublishedGame> getAllPublishedGames() {
        return pgr.findAll();
    }

    public void deletePublishedGame(int game_id) {
        PublishedGame pg = pgr.findById(game_id).get();
        scr.deleteAll(scr.findScoreCardByPublishedGame(pg));
        pgr.delete(pg);
    }
}
