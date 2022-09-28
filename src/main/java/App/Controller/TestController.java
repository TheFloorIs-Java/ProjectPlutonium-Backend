package App.Controller;

import App.Service.SpringTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final SpringTestService sts;

    @Autowired
    public TestController(SpringTestService sts){

        this.sts = sts;
    }
    @GetMapping("/")
    public String hello(){
        sts.TestMethod();
        return "Hello API!";
    }
}
