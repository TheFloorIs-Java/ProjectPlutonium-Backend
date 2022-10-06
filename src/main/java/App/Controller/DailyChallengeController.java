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
    public DailyChallenge postDailyChallengeByDateId(@RequestBody DailyChallenge dc) {
        return dcs.addDailyChallenge(dc);
    }

}
