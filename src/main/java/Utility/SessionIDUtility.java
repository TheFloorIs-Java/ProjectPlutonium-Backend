package Utility;

import java.util.UUID;

public class SessionIDUtility {

    //Generates a random UUID.
    public static UUID getSessionID(){
        return UUID.randomUUID();
    }

}