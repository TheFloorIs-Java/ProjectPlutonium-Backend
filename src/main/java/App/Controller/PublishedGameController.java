package App.Controller;

import App.Service.PublishedGameService;
import App.Service.SpringTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublishedGameController {

    private final PublishedGameService pgs;

    @Autowired
    public PublishedGameController(PublishedGameService pgs){
        this.pgs = pgs;
    }
    @GetMapping("/publishedGames/id/{id}")
    public String hello(){
        return "Hello API!";
    }
}