package App.Models;

public class PublishedGame {
    private int id;
    private String title;
    private String gameType;
    private String gameData;
    private User user;
    private int numberOfPlays;

    public PublishedGame() {
        this.id = -1;
        this.title = "No Title";
        this.gameType = "No Type";
        this.gameData = "No Data";
        this.user = new User();
        this.numberOfPlays = -1;
    }

    public PublishedGame(int id, String title, String gameType, String gameData, User user, int numberOfPlays) {
        this.id = id;
        this.title = title;
        this.gameType = gameType;
        this.gameData = gameData;
        this.user = user;
        this.numberOfPlays = numberOfPlays;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public String getGameData() {
        return gameData;
    }

    public void setGameData(String gameData) {
        this.gameData = gameData;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getNumberOfPlays() {
        return numberOfPlays;
    }

    public void setNumberOfPlays(int numberOfPlays) {
        this.numberOfPlays = numberOfPlays;
    }
}
