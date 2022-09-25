package DAO;

import Models.User;
import Utility.ConnectionUtil;
import java.sql.*;

public class UserDAO {
    Connection connection = ConnectionUtil.getConnection();

    //================================================== USER INFO ==================================================

    /*
        Adds a new user to the user_info table
        Parameter: User object
        Returns: boolean value
    */
    public boolean addUser(User user) {
        String query = "INSERT INTO user_info (username, password, salt, permission_level) VALUES (?, ?, ?, 0)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getSalt());
            return preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Something went wrong while adding the user: " + user.getUsername());
        }
        return false;
    }

    /*
        Authenticate if the user login credentials
        Parameter: User Object
        Returns: boolen true if the user credentials match in database and false if user credentials do not match.
    */
    public boolean authenticateUser(User user){
        String query = "SELECT * FROM user_info WHERE username = ? AND password = ? AND salt = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
            System.out.println(user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            System.out.println(user.getPassword());
            preparedStatement.setString(3, user.getSalt());
            System.out.println(user.getSalt());
            return preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Something went wrong while authenticating the user credentials: "+user.getUsername());
        }
        return false;
    }


    /*
        Get a user's information from the user_info table
        Parameter: Username
        Returns: User object with the username, userID, and permissionLevel properties filled set.
    */
    public User getUser(String username) {
        String query = "SELECT user_id, username, permission_level FROM user_info WHERE username = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3));
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong while adding the user: " + username);
        }
        return null;
    }

    /*
        Get a user's information from the user_info table
        Parameter: User_ID
        Returns: User object with the username, userID, and permissionLevel properties filled set.
    */
    public User getUser(int user_id) {
        String query = "SELECT user_id, username, permission_level FROM user_info WHERE user_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, user_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3));
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong while adding the user: " + user_id);
        }
        return null;
    }

    /*
        Get a user's information from the user_info table and sessions table
        Parameter: Username
        Returns: User object with the username, userID, permissionLevel properties, session ID, and session expiry
                 properties set.
    */
    public User getUserDetailed(String username) {
        String query = "SELECT user_info.user_id, username, permission_level, session_id, session_expiry " +
                            "FROM user_info JOIN sessions " +
                            "ON user_info.user_id = sessions.user_id " +
                            "WHERE user_info.username = ? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new User(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getInt(3), resultSet.getString(4),
                        resultSet.getString(5));
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong while getting active user info for username: " + username);
        }
        return null;
    }

    /*
       Get a user's information from the user_info table and sessions table
       Parameter: User ID
       Returns: User object with the username, userID, permissionLevel properties, session ID, and session expiry
                properties set.
   */
    public User getUserDetailed(int userID) {
        String query = "SELECT user_info.user_id, username, permission_level, session_id, session_expiry " +
                "FROM user_info JOIN sessions " +
                "ON user_info.user_id = sessions.user_id " +
                "WHERE user_info.user_id = ? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new User(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getInt(3), resultSet.getString(4),
                        resultSet.getString(5));
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong while getting active user info for userID: " + userID);
        }
        return null;
    }

    /*
        Get a user's salt value from the user_info table
        Parameter: User Obj
        Returns: String the salt associated with a user.
    */
    public String getSalt(User user){
        String query = "SELECT salt FROM user_info WHERE username = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return resultSet.getString(1);
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong while getting the salt value for user: "+user.getUsername());
        }
        return null;
    }

    /*
        Method to update the password
        Parameter: User Obj
        Returns: boolean true if the password is updated and false if the password does not update
    */
    public boolean updatePassword(User user){
        String query = "UPDATE user_info SET password = ? AND salt = ? WHERE username = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getSalt());
            preparedStatement.setString(3, user.getUsername());
            return preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Something went wrong while updating the password");
            return false;
        }
    }

    /*
        Method to update a user's permission level
        Parameter: User Obj
        Returns: boolean true if the user has admin permission otherwise it will return false
    */
    public boolean updatePermissionLevel(String username, int permissionLevel){
        String query = "UPDATE user_info SET permission_level = ? WHERE username = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, permissionLevel);
            preparedStatement.setString(2, username);
            return preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Something went wrong while changing permission level for user: "+username);
        }
        return false;
    }



    //================================================== SESSION INFO ==================================================

    /*
        Get Session Info on an active user
        Parameter: Session ID
        Returns: User object with user_id, session_id, session_expiry
    */
    public User getSessionInfo(String sessionID) {
        String query = "SELECT * FROM sessions WHERE session_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, sessionID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user  = getUser(resultSet.getInt(1));
                user.setSessionId(resultSet.getString(2));
                user.setSessionExpiration(resultSet.getString(3));
                return user;
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong while getting session information for session_id: " + sessionID);
        }

        return null;
    }

    /*
        Set Session Info of an active user
        Parameter: User ID, Session ID, Session_Expiry
        Returns: boolean so that we can check if the update was made.
    */
    public boolean setSessionInfo(User user, String sessionID, String sessionExpiry) {
        user = getUser(user.getUsername());
        String query = "INSERT INTO sessions (user_id, session_id, session_expiry) VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, sessionID);
            preparedStatement.setString(3, sessionExpiry);
            return preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Something went wrong while updating the Session Info for user ID: " + user.getId());
        }
        return false;
    }

    /*
        Delete Session Info of an active user
        Parameter: User ID, Session ID, Session_Expiry
        Returns: boolean so that we can check if the update was made.
    */
    public boolean deleteSessionInfo(String sessionID) {
        String query = "DELETE FROM sessions WHERE session_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, sessionID);
            return preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Something went wrong while deleting session record for:" + sessionID);
        }
        return false;
    }

}