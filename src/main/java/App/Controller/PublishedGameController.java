package App.Controller;
import App.Models.PublishedGame;
import App.Service.PublishedGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class PublishedGameController {

    private final PublishedGameService pgs;

    @Autowired
    public PublishedGameController(PublishedGameService pgs){
        this.pgs = pgs;
    }

    //Creates a new publishedGame
    @PostMapping("publishedGames/")
    public void addPublishedGame(@RequestBody PublishedGame pg) {
        pgs.addPublishedGame(pg);
    }
    //Grabs a game by the id
    @GetMapping("/publishedGames/id/{id}")
    public Optional<PublishedGame> getPublishedGameById(@PathVariable("id") int id){
        Optional<PublishedGame> pg = pgs.getPublishedGameById(id);
        return pg;
    }

    //Grabs a list of games made by a user
    @GetMapping("/publishedGames/userId/{id}")
    public List<PublishedGame> getPublishedGamesById(@PathVariable("id") int userid) {
        List<PublishedGame> lpg = pgs.getPublishedGamesById(userid);
        return lpg;
    }
}