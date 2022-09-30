package App.Controller;

import App.Models.DailyChallenge;
import App.Service.DailyChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class DailyChallengeController {

    private final DailyChallengeService dcs;

    @Autowired
    public DailyChallengeController(DailyChallengeService dcs) {

        this.dcs = dcs;

    }

    @GetMapping("/dailyChallenge/{date}")
    public Optional<DailyChallenge> getDailyChallengeByDate(@PathVariable("date") int date) {
        return dcs.getDailyChallengeByDate(date);
    }
}
