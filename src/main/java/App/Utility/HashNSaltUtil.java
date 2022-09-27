package App.Utility;

import org.mindrot.jbcrypt.BCrypt;

public final class HashNSaltUtil {

    private HashNSaltUtil(){
        throw new IllegalStateException("cannot be instantiated");
    }

    public static String saltAndHash(String password, String salt){
        return hash(password, salt);
    }

    private static String hash(String password, String salt){
        return BCrypt.hashpw(password, salt);
    }

    public static String generateSalt(){
        return BCrypt.gensalt();
    }

}