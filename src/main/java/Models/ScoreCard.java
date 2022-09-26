package Models;

import java.util.Date;

public class ScoreCard {
    private User user;
    private PublishedGame publishedGame;
    private Date date;
    private int score;

    public ScoreCard() {
        this.user = new User();
        this.publishedGame = new PublishedGame();
        this.date = new Date(0);
        this.score = -1;
    }

    public ScoreCard(User user, PublishedGame publishedGame, Date date, int score) {
        this.user = user;
        this.publishedGame = publishedGame;
        this.date = date;
        this.score = score;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PublishedGame getPublishedGame() {
        return publishedGame;
    }

    public void setPublishedGame(PublishedGame publishedGame) {
        this.publishedGame = publishedGame;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
