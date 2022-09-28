import App.Models.User;
import App.Service.UserService;
import App.Utility.SessionIDUtil;
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


        //Login Endpoint --> /login
        app.post("/login", ctx ->{
            User user = mapper.readValue(ctx.body(), User.class);
            if (userService.authenticateUser(user)){
                userService.setSessionID(user);
                ctx.json(userService.getUserInfo(user.getUsername()));
                ctx.status(200);
            }
        } );

        //Create User Endpoint --> /createaccount
        app.post("/createaccount", ctx ->{
            User user = mapper.readValue(ctx.body(), User.class);
            if (userService.createUser(user)){
                userService.setSessionID(user);
                ctx.json(userService.getUserInfo(user.getUsername()));
                ctx.status(200);
            }
        } );

        //Get User Info --> /userinfo
        app.get("/userinfo", ctx -> {
            //Get the session id
            User user = userService.getSessionInfo(ctx.header("session"));
            //Check if the session has expired or not.
            if (SessionIDUtil.checkSession(user)){
                //Get the user id associated with the sessionID
                ctx.json(userService.getUserInfo(user.getId()));
            }
            else{
                userService.deleteSession(user.getSessionId());
                ctx.status(401);
            }
        });

        //Update Password by User ---> /updatepassword
        app.put("/updatepassword", ctx -> {
            //Get the session Id
            User user = userService.getSessionInfo(ctx.header("session"));
            //User will be sending their old password as well in the header
            user.setPassword(ctx.header("opwd"));

            //Check if the session has expired or not
            if (SessionIDUtil.checkSession(user)){
                if(userService.updatePassword(user, ctx.header("npwd"))){
                    ctx.status(200);
                }
                else {
                    ctx.status(503);
                }
            }
            else {
                userService.deleteSession(user.getSessionId());
                ctx.status(401);
            }
        });

        //Change user's permission level --> /changepermissionlevel
        app.put("/changepermissionlevel", ctx -> {
            User user = userService.getSessionInfo(ctx.header("session"));

            if (SessionIDUtil.checkSession(user)){
                if (userService.changePermissionLevel(user, ctx.header("username"), Integer.parseInt(ctx.header("permissionLevel")))){
                    ctx.status(200);
                }
                else {
                    ctx.status(401);
                }
            }
            else {
                ctx.status(401);
            }
        });






    }
}