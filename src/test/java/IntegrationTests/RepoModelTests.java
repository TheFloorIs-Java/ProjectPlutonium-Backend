package IntegrationTests;


import App.App;
import App.DAO.ScoreCardRepository;
import App.Service.ScoreCardService;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(
        SpringBootTest.WebEnvironment.MOCK,
        classes = App.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")

public class RepoModelTests {

    ScoreCardRepository scr;
    static ScoreCardService scs;




}
