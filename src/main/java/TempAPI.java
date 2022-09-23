import Models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;


public class TempAPI {


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

        //post login/ ( supply username and password and receive User object )
        app.post("/login", ctx -> {
            ObjectMapper mapper = new ObjectMapper();
            User requestUser = mapper.readValue(ctx.body(), User.class);
            ctx.result("Test");
        });

        //get user/id
        app.get("/users/id/{id}", ctx -> ctx.json(new User()));

        app.get("/users/all", ctx -> ctx.json(new User[]{new User(), new User(), new User()}));

        //get user/username
        app.get("/users/username/{name}", ctx -> ctx.json(new User()));

        //post register/ ( supply User object and Service will add it to database )
        app.post("/register", ctx -> {
            ObjectMapper mapper = new ObjectMapper();
            User requestUser = mapper.readValue(ctx.body(), User.class);
            ctx.result("Test");
        });

        app.get("/hangman/date/{date}", ctx -> ctx.result("this is the test game. try your best!"));

    }
}