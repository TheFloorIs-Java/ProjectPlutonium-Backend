import Models.User;
import Service.UserService;
import Utility.HashNSaltUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.core.JavalinConfig;

public class Main {
    public static void main(String[] args) {
        //Initializing the user service class object once because the service object is independent
        // of the user accessing the object. The user service will take the user (model) object
        UserService userService = new UserService();

        ObjectMapper mapper = new ObjectMapper();

        Javalin app = Javalin.create(JavalinConfig::enableCorsForAllOrigins).start(8080);


        //========================================= END POINTS START =========================================


        //Login Endpoint --> /projectplutonium/login
        app.post("/projectplutonium/login", ctx ->{
            User user = mapper.readValue(ctx.body(), User.class);
            if (userService.authenticateUser(user)){
                userService.setSessionID(user);
                ctx.json(userService.getUserInfo(user.getUsername()));
                ctx.status(200);
            }
        } );

        //Create User Endpoint --> /projectplutonium/createaccount
        app.post("/projectplutonium/createaccount", ctx ->{
            User user = mapper.readValue(ctx.body(), User.class);
            if (userService.createUser(user)){
                userService.setSessionID(user);
                ctx.json(userService.getUserInfo(user.getUsername()));
                ctx.status(200);
            }
        } );

        //Get User Info --> /projectplutonium/userinfo
        app.get("/projectplutonium/userinfo", ctx -> {
            //Get the session id
            User user = userService.getSessionInfo(ctx.cookie("sessionID"));
            //Check if the session has expired or not.
            if (user != null){ //remove this condition
                //Get the user id associated with the sessionID
                ctx.json(userService.getUserInfo(user.getId()));
            }
            else{
                ctx.status(401);
            }
        });
    }
}