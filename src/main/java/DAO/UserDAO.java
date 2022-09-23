package DAO;

import Models.User;
import Utility.ConnectionUtil;
import java.sql.*;

public class UserDAO {
    Connection connection = ConnectionUtil.getConnection();

    /*
        Adds a new user to the user_info table
        Parameter: User object
        Returns: boolean value
    */
    public boolean addUser(User user){
        String query = "INSERT INTO user_info (username, password, salt, permission_level) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getSalt());
            preparedStatement.setInt(4, user.getPermissionLevel());
            return preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Something went wrong while adding the user: "+user.getUsername());
        }
        return false;
    }

    /*
        Get a user's information from the user_info table
        Parameter: Username
        Returns: User object with the username, userID, and permissionLevel properties filled out.
    */
    public User getUser(String username){
        String query = "SELECT FROM user_info WHERE username = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return new User( resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(5));
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong while adding the user: "+username);
        }
        return null;
    }

    /*
        Get a user's information from the user_info table
        Parameter: User_ID
        Returns: User object with the username, userID, and permissionLevel properties filled out.
    */
    public User getUser(int user_id){
        String query = "SELECT FROM user_info WHERE user_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, user_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return new User( resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(5));
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong while adding the user: "+user_id);
        }
        return null;
    }

    /*
        Get a user's information from the user_info table
        Parameter: User_ID
        Returns: User object with the username, userID, and permissionLevel properties filled out.
    */
    public User getUserDetailed(String username){
        String query =  "SELECT user_info.user_id, username, permission_level, session_id, session_expiry " +
                        "FROM user_info JOIN sessions " +
                        "ON user_info.user_id = sessions.user_id" +
                        "WHERE user_info.username = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new User(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getInt(3), resultSet.getString(4),
                        resultSet.getDate(5));
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong while getting active user info for username: "+username);
        }
        return null;
    }
    /*
        Get Session Info on an active user
        Parameter: Session ID
        Returns: User object with user_id, session_id, session_expiry
    */
    public User getSessionInfo(String sessionID){
        String query = "SELECT FROM sessions WHERE session_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, sessionID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getTime(3));
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong while getting session information for session_id: "+sessionID);
        }

        return null;
    }

    /*
        Set Session Info of an active user
        Parameter: User ID, Session ID, Session_Expiry
        Returns: boolean so that we can check if the update was made.
    */
    public boolean setSessionInfo(int userID, String sessionID, Date sessionExpiry){
        String query = "INSERT INTO sessions (user_id, session_id, session_expiry) VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userID);
            preparedStatement.setString(2, sessionID);
            preparedStatement.setDate(3, sessionExpiry);
            return preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Something went wrong while updating the Session Info for user ID: "+userID);
        }
        return false;
    }
    //Update Session Info
    public User updateSessionInfo(){
        return null;
    }

    /*
        Delete Session Info of an active user
        Parameter: User ID, Session ID, Session_Expiry
        Returns: boolean so that we can check if the update was made.
    */
    public boolean deleteSessionInfo(int userID){
        String query = "DELETE FROM sessions WHERE user_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userID);
            return preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Something went wrong while deleting session record for user ID:"+userID);
        }
        return false;
    }
}