package Utility;

import java.util.UUID;

public class SessionIDUtil {

    //Generates a random UUID.
    public static UUID getSessionID(){
        return UUID.randomUUID();
    }

}