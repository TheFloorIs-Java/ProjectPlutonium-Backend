package App.Controller;

import App.Models.DailyChallenge;
import App.Models.PublishedGame;
import App.Service.DailyChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
public class DailyChallengeController {

    private final DailyChallengeService dcs;

    @Autowired
    public DailyChallengeController(DailyChallengeService dcs) {

        this.dcs = dcs;

    }

    @GetMapping("/dailyChallenge/{date}")
    public DailyChallenge getDailyChallengeByDate(@PathVariable("date") @DateTimeFormat(pattern="yyyy-MM-dd") Date date) {
        return dcs.getDailyChallengeByDate(date);
    }

    @GetMapping("/dailyChallenge/all")
    public List<DailyChallenge> getAllDailyChallenges(){
        return dcs.getAllDailyChallenges();
    }

    @DeleteMapping("/dailyChallenge/{id}")
    public List<DailyChallenge> removeDailyChallengeById(@PathVariable("id") int id){
        return dcs.removeDailyChallengeById(id);
    }

    @PostMapping("/dailyChallenge/")
    public DailyChallenge postDailyChallengeByDateId(@RequestHeader Map<String, String> headers) {

        Date pd = null;
        String date = headers.get("challenge_date");
        int id = Integer .parseInt(headers.get("game_id"));

        PublishedGame pg = new PublishedGame();
        pg.setGame_id(id);

        try {
            pd = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        }catch (Exception e){
            System.out.println("e");
        }

        DailyChallenge dc = new DailyChallenge();
        dc.setChallengeDate(pd);
        dc.setPublished_game(pg);


        return dcs.addDailyChallenge(dc);
    }

}
