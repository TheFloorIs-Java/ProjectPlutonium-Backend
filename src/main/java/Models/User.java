package Models;

import java.util.Date;

public class User {
    private int id, permissionLevel;
    private String username, password, sessionId, salt, sessionExpiration;


    public User() {
        this.id = -1;
        this.username = "missing";
        this.password = "*****";
        this.permissionLevel = 0;
        this.salt = "*****";
        this.sessionId = "missing";
        this.sessionExpiration = null;
    }
    
    public User(int id, String username, String password, int permissionLevel, String salt, String sessionId, String sessionExpiration) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.permissionLevel = permissionLevel;
        this.salt = salt;
        this.sessionId = sessionId;
        this.sessionExpiration = sessionExpiration;
    }

    public User(int id, String username, int permissionLevel, String sessionId, String sessionExpiration) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.permissionLevel = permissionLevel;
        this.salt = salt;
        this.sessionId = sessionId;
        this.sessionExpiration = sessionExpiration;
    }

    public User(int id, String username, int permissionLevel) {
        this.id = id;
        this.username = username;
        this.password = "*****";
        this.permissionLevel = permissionLevel;
        this.salt = "*****";
        this.sessionId = "missing";
        this.sessionExpiration = null;
    }

    public User(int id, String sessionId, String sessionExpiration) {
        this.id = id;
        this.username = "";
        this.password = "*****";
        this.permissionLevel = 0;
        this.salt = "*****";
        this.sessionId = sessionId;
        this.sessionExpiration = sessionExpiration;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(int permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionExpiration() {
        return sessionExpiration;
    }

    public void setSessionExpiration(String sessionExpiration) {
        this.sessionExpiration = sessionExpiration;
    }


}