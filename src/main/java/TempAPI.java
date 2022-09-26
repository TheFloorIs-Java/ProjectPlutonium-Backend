import Models.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;


public class TempAPI {

    static User[] users = {
            new User(1, "Matt", "*****", 1, "*****", null, null),
            new User(2, "Raj", "*****", 1, "*****", null, null),
            new User(3, "Natalia", "*****", 1, "*****", null, null),
            new User(4, "Jacob", "*****", 1, "*****", null, null),
            new User(5, "Corey", "*****", 1, "*****", null, null)
    };
    static PublishedGame publishedGame = new PublishedGame(1, "Test Game Title", "Type", "[{'description':'Seek shelter','map':'../../../assets/images/map.png','actions':[{'actionLabel':'search the pizzeria','index':1},{'actionLabel':'search the neaby appartment','index':2},{'actionLabel':'search the mall','index':3}]},{'description':'You are in the pizzeria','map':'../../../assets/images/map.png','actions':[{'actionLabel':'check the oven','index':2},{'actionLabel':'check the perimeter','index':3},{'actionLabel':'step back outside','index':0}]},{'description':'Test 1','map':'../../../assets/images/map.png','actions':[{'actionLabel':'search the pizzaria','index':1},{'actionLabel':'search the neaby appartment','index':2},{'actionLabel':'search the mall','index':3}]},{'description':'Seek shelter','map':'../../../assets/images/map.png','actions':[{'actionLabel':'search the pizzaria','index':1},{'actionLabel':'search the neaby appartment','index':2},{'actionLabel':'search the mall','index':3}]}]", users[2], 6);

    static ScoreCard[] scoreCards = {
            new ScoreCard(users[0], publishedGame, null, 5),
            new ScoreCard(users[1], publishedGame, null, 10),
            new ScoreCard(users[2], publishedGame, null, 8),
            new ScoreCard(users[3], publishedGame, null, 4),
            new ScoreCard(users[4], publishedGame, null, 20)
    };

    static HighScoreCard[] highScoreCards = {
            new HighScoreCard(users[0], 5),
            new HighScoreCard(users[1], 10),
            new HighScoreCard(users[2], 8),
            new HighScoreCard(users[3], 4),
            new HighScoreCard(users[4], 20)
    };

    public static void main(String[] args) {

        System.out.println("Hello World!");

        Javalin app = Javalin.create(config -> {
            config.enableCorsForAllOrigins();
        }).start(80);




        app.before(ctx -> {
            ctx.header("Access-Control-Allow-Credentials", "true");
            //ctx.header("Access-Control-Allow-Origin", "http://127.0.0.1:5500");
        });


        app.get("/", ctx -> ctx.result("Hello World!"));




        //get users/id
        app.get("/users/id/{id}", ctx -> ctx.json(users[Integer.parseInt(ctx.pathParam("id"))-1]));
        app.get("/users/session", ctx -> {
           String sid = ctx.header("session");
           if (sid != null && sid != "") ctx.json(users[0]);
           else ctx.status(401);
        });
        app.get("/users/all", ctx -> ctx.json(users));


        app.post("/login", ctx -> {
            ObjectMapper mapper = new ObjectMapper();
            User requestUser = mapper.readValue(ctx.body(), User.class);
            requestUser.setSessionId("ThisWouldBeASessionID");
            ctx.json(requestUser);
        });
        app.post("/users", ctx -> {
            ObjectMapper mapper = new ObjectMapper();
            User requestUser = mapper.readValue(ctx.body(), User.class);
            requestUser.setSessionId("ThisWouldBeASessionID");
            ctx.json(requestUser);
        });
        app.put("/users/id/{id}", ctx -> ctx.status(200));

        app.get("/publishedGames/id/{id}", ctx -> ctx.json(publishedGame));
        app.get("/publishedGames/userId/{id}", ctx -> ctx.json(new PublishedGame[] {publishedGame, publishedGame}));
        app.get("/publishedGames/date/{date}", ctx -> ctx.json(publishedGame));


        app.get("/scoreCards/userId/{id}", ctx -> ctx.json(new ScoreCard[] {scoreCards[Integer.parseInt(ctx.pathParam("id"))], scoreCards[Integer.parseInt(ctx.pathParam("id"))], scoreCards[Integer.parseInt(ctx.pathParam("id"))]}));
        app.post("/scoreCards", ctx -> ctx.status(200));

        app.get("/highScoreCards/all", ctx -> ctx.json(highScoreCards));


        app.get("/echo", ctx -> ctx.result(ctx.body()));
        app.post("/echo", ctx -> ctx.result(ctx.body()));






        // Depreciated===================================================================================
        app.post("/publishedGame", ctx -> {
            ObjectMapper mapper = new ObjectMapper();
            PublishedGame requestGame = mapper.readValue(ctx.body(), PublishedGame.class);
            ctx.json(requestGame);
        });

        app.get("/hangman/date/{date}", ctx -> ctx.result("this is the test game. try your best!"));


    }
}