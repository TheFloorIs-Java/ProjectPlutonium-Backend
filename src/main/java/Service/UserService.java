package Service;

import DAO.UserDAO;
import Models.User;
import Utility.HashNSaltUtil;
import Utility.SessionIDUtil;

import java.util.Date;
import java.util.Set;

//********* From the client side we will receive a user object or make one when the request is received **********:
public class UserService {

    private UserDAO userDAO;

    public UserService(){
        userDAO = new UserDAO();
    }



    /*
        Method will check if the user exists in the system by checking the DAO.
        Parameter: User obj.
        The user object should have the following properties set:
            1. username 2. password
        Returns: boolean true is user exists and false if user does not exist.
    */
    public boolean authenticateUser(User user){
        //setting the salt in the user object.
        user.setSalt(userDAO.getSalt(user));

        // replacing the user's password with the hashed version
        user.setPassword(HashNSaltUtil.saltAndHash(user.getPassword(), user.getSalt()));

        return userDAO.authenticateUser(user);
    }

    /*
        Method will Create a new user
        Parameters: User obj
        The user object should have the following properties:
            1. user_id  2. username  3. password  4. permission_level
        Return: boolean true if the user is added and false if the user did not add
    */
    public boolean createUser(User user){
        //Maybe --->Check if the username exists then create.

        //setting the salt for the user
        user.setSalt(HashNSaltUtil.generateSalt());

        //replacing the user's password with the hashed version
        user.setPassword(HashNSaltUtil.saltAndHash(user.getPassword(), user.getSalt()));

        return userDAO.addUser(user);
    }

    /*
       Method to update password if the user wishes to do it.
       Parameters: Uer obj
       The user obj should have at least the following properties:
            1. username 2. password
       Returns: boolean true if the password updated or false if something goes wrong
    */
    public boolean updatePassword(User user , String newPassword){
        user.setSalt(userDAO.getSalt(user));
        user.setPassword(HashNSaltUtil.saltAndHash(user.getPassword(), user.getSalt()));

        //check if the current credentials match the database before changing the password
        if (userDAO.authenticateUser(user)){
            //getting a new salt and hashing the new password
            user.setSalt(HashNSaltUtil.generateSalt());
            user.setPassword(HashNSaltUtil.saltAndHash(newPassword, user.getSalt()));
            return userDAO.updatePassword(user);
        }
        else {
            return false;
        }
    }

    /*
        Method for an admin to change user password without the old user password
        Parameter: User Obj
        The user obj should have at least the following property:
            1. username
        Returns: boolean true if the password is set and false if something goes wrong
    */
    public boolean updateAPassword(User user, String newPassword){
        //getting a new salt and hashing the new password
        user.setSalt(HashNSaltUtil.generateSalt());
        user.setPassword(HashNSaltUtil.saltAndHash(newPassword, user.getSalt()));
        return userDAO.updatePassword(user);
    }

    /*
        Method for Admins to change a user's permission level
        Parameters: User1 Obj, (Admin) User2 Obj
        The user Obj should atleast have the following property:
            1. username
        The (admin) user obj should at least have the following property:
            1. username 2. permission_level
        Returns: boolean true if the admin successfully changes a user's permission level otherwise false.
    */
    public boolean changePermissionLevel( User admin, String username, int permissionLevel){
        //To make sure if the user changing the permission level is an admin.
        if (checkPermissionLevel(admin)){
            //if true then user with username will have their permission level changed
            return userDAO.updatePermissionLevel(username, permissionLevel);
        }
        return false;
    }

    /*
        Method to check a user's permission level
        Parameter: User Obj
        The user obj should have at least the following properties:
                1. username
        Returns: boolean true if the user has admin permission otherwise it will return false
    */
    public boolean checkPermissionLevel(User user){
        user = userDAO.getUser(user.getUsername());
        return user.getPermissionLevel() == 1;
    }

    /*
        Method to get User info based on the session id
        Parameter: Session ID
        Returns: User obj with the following properties if the session is legit:
            1. username 2. user_id 3. permission_level 4. sessionID 5. sessionExpiry
            if session ID is incorrect or not associated with an user then the user obj will be null
    */
    public User getSessionInfo(String sessionID){
        return userDAO.getSessionInfo(sessionID);
    }

    /*
        Set A session ID and Expiry which will put the user in the active user table
        Parameter: User obj
        the user object should have atleast the following properties
            1. username
        Returns: boolean true if a session id is set otherwise it will return false
     */
    public boolean setSessionID(User user){
        String sessionID = SessionIDUtil.getSessionID().toString();
        Date date = new Date();
        String expiry = String.valueOf((date.getTime()+3600000));
        return userDAO.setSessionInfo(user, sessionID, expiry);
    }

    //Update the Session ID after expiry or a password change


    /*
       Method to Delete a session
       Parameter: User obj
       the user object should have at least the following properties
           1. username
       Returns: boolean true if a session id is deleted otherwise it will return false
    */
    public boolean deleteSession(String sessionID){
        return userDAO.deleteSessionInfo(sessionID);
    }

}