package Models;

public class HighScoreCard {
    private User user;
    private int score;

    public HighScoreCard() {
        this.user = new User();
        this.score = -1;
    }

    public HighScoreCard(User user, int score) {
        this.user = user;
        this.score = score;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
