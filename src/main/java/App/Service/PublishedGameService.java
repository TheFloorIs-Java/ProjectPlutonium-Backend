package App.Service;

import App.DAO.PublishedGameRepository;
import App.Models.DailyChallenge;
import App.Models.PublishedGame;
import App.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class PublishedGameService {
    @Autowired
    PublishedGameRepository pgr;

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
}
