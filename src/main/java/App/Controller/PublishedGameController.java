package App.Controller;

import App.Models.PublishedGame;
import App.Service.PublishedGameService;
import App.Service.SpringTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PublishedGameController {

    private final PublishedGameService pgs;

    @Autowired
    public PublishedGameController(PublishedGameService pgs){
        this.pgs = pgs;
    }
    @GetMapping("/publishedGames/id/{id}")
    public PublishedGame getPublishedGameById(@PathVariable("id") int id){
        PublishedGame pg = pgs.getPublishedGameById(id);
        return pg;
    }

    @GetMapping("/publishedGames/userId/{id}")
    public List<PublishedGame> getPublishedGamesById(@PathVariable("id") int userid) {
        ArrayList<PublishedGame> lgm = pgs.getPublishedGamesById(userid);
        return lgm;
    }

    @GetMapping("/publishedGames/date/{date}")
    public PublishedGame getGameOfTheDayByDate(@PathVariable("date") String date){
        PublishedGame gm = pgs.getGameOfTheDayByDate(date);
        return gm;
    }
}