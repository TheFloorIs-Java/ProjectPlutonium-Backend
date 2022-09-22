package API;


import Models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;


public class TempAPI {


    public static void main(String[] args) {

        System.out.println("Hello World!");

        Javalin app = Javalin.create(config -> {
            config.enableCorsForAllOrigins();
        }).start(7070);


        app.before(ctx -> {
            ctx.header("Access-Control-Allow-Credentials", "true");
            //ctx.header("Access-Control-Allow-Origin", "http://127.0.0.1:5500");
        });


        //post login/ ( supply username and password and receive User object )
        app.post("/login", ctx -> {
            ObjectMapper mapper = new ObjectMapper();
            User requestUser = mapper.readValue(ctx.body(), User.class);
            ctx.result("Test");
        });

        //get user/id
        app.get("/user/id/{id}", ctx -> ctx.json(new User()));

        //get user/username
        app.get("/user/username/{name}", ctx -> ctx.json(new User()));

        //post register/ ( supply User object and Service will add it to database )
        app.post("/register", ctx -> {
            ObjectMapper mapper = new ObjectMapper();
            User requestUser = mapper.readValue(ctx.body(), User.class);
            ctx.result("Test");
        });

    }
}
