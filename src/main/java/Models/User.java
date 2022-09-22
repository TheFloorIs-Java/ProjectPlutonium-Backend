package Models;

public class User {
    int id;
    String username;
    String password;
    String permissionLevel;
    String salt;

    public User() {
    }

    public User(int id, String username, String password, String permissionLevel, String salt, String sessionId, String sessionExpiration) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.permissionLevel = permissionLevel;
        this.salt = salt;
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

    public String getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(String permissionLevel) {
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

    String sessionId;
    String sessionExpiration;
}