package App.Controller;

import App.Models.ScoreCard;
import App.Service.ScoreCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class ScoreCardController {

    ScoreCardService scs;

    //Dependency Injection
    @Autowired
    public ScoreCardController(ScoreCardService scs){
        this.scs = scs;
    }

    @GetMapping("/scorecard/id")
    public List<ScoreCard> getScoreCardByPlayerId(@RequestHeader Map<String, String> id){
        return scs.getScoreCardByPlayerId(Integer.parseInt(id.get("id")));
    }

    @PostMapping("/scorecard")
    public ScoreCard addScoreByPlayerId(@RequestHeader Map<String, String> headers){
        return scs.addScoreCard(Integer.parseInt(headers.get("score")),
                Integer.parseInt(headers.get("user_id")),
                Integer.parseInt(headers.get("game_id")));
    }

}
