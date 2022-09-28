package App.Service;

import App.Models.DailyChallenge;
import App.Models.PublishedGame;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Component
public class PublishedGameService {
    public PublishedGame getPublishedGameById(int id) {
        return null;
    }

    public ArrayList<PublishedGame> getPublishedGamesById(int userid) {
        return null;
    }

    public DailyChallenge getDailyChallengeByDate(String date) {
        return null;
    }
}
