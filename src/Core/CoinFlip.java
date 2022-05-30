package Core;

import java.util.Random;

public class CoinFlip {

    private int streak, highscore;
    private Random random;
    private User user;
    private DatabaseConnector dbc;

    public CoinFlip(User user, DatabaseConnector databaseConnector) {
        streak = 0;
        highscore = user.getCfScore();
        this.dbc = databaseConnector;
        this.user = user;
        random = new Random();
    }

    public int flip(int guess) {
        int result = random.nextInt(2);

        if (result == guess) {
            streak++;
        } else {
            if (streak > highscore) {
                user.setCfScore(streak);
                dbc.updateScore(user);
            }
            streak = 0;
        }
        return result;
    }

    public int getStreak() {
        return streak;
    }

    public int getHighscore() {
        return highscore;
    }
}
