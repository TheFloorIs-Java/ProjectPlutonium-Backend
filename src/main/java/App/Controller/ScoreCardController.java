package App.Controller;

import App.Models.ScoreCard;
import App.Service.ScoreCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ScoreCard getScoreCardByPlayerId(){
        return scs.getScoreCardByPlayerId();
    }

    @PostMapping("/scorecard")
    public ScoreCard addScoreByPlayerId(@RequestBody ScoreCard scoreCard){
        scs.addScoreCard(scoreCard);
        return scs.getScoreCardByPlayerId();
    }

}
