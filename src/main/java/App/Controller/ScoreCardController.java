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

    @GetMapping("/scorecard/all")
    public List<ScoreCard> getAllScoreCards(){
        return scs.getAllScoreCards();
    }

    @GetMapping("/scorecard/userId")
    public List<ScoreCard> getScoreCardByPlayerId(@RequestHeader Map<String, String> id){
        return scs.getScoreCardByPlayerId(Integer.parseInt(id.get("id")));
    }

    @PostMapping("/scorecard")
    public ScoreCard addScoreByPlayerId(@RequestBody ScoreCard scoreCard){
        return scs.addScoreCard(scoreCard);
    }

    @GetMapping("/leaderboard")
    public List<ScoreCard> getLeaderBoardDetails(){
        return scs.getAggregateScores();
    }

}
