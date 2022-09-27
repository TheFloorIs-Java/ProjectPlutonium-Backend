package Utility;

import Models.User;

import java.util.Date;
import java.util.UUID;

public class SessionIDUtil {

    //Generates a random UUID.
    public static UUID getSessionID(){
        return UUID.randomUUID();
    }

    /*
        Method to check if the session has expired or not
        Parameter: User object
        the user object has atleast the following properties
            1. user ID  2. session ID  3. session Expiry
        OutPut: boolean true if the session is valid or false otherwise.
    */
    public static boolean checkSession(User user){
        //check if the session has expired or not.
        Date dateuser = new Date(Long.parseLong(user.getSessionExpiration()));
        Date datenow = new Date();
        if (datenow.after(dateuser)){
            return false;
        }
        return true;
    }

    public static boolean checkSession(String date){
        //check if the session has expired or not.
        Date dateuser = new Date(Long.parseLong(date));
        Date datenow = new Date();
        if (datenow.after(dateuser)){
            return false;
        }
        return true;
    }
}