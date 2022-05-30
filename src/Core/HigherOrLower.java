package Core;

import java.util.Random;

public class HigherOrLower {

    private int streak, highscore, current, next;
    private Random random;
    private User user;
    private DatabaseConnector dbc;

    public HigherOrLower(User user, DatabaseConnector databaseConnector) {
        streak = 0;
        highscore = user.getHlScore();
        this.dbc = databaseConnector;
        this.user = user;
        random = new Random();
        current = random.nextInt(13)+1;
    }

    public void guess(int choice) {
        next = random.nextInt(13)+1;
        if (choice == 0 && current >= next) {
            streak++;
        } else if (choice == 1 && current <= next) {
            streak++;
        } else {
            if (streak > highscore) {
                user.setHlScore(streak);
                dbc.updateScore(user);
            }
            streak = 0;
        }
        current = next;
    }

    public int getCurrent() {
        return current;
    }
    public int getNext() {
        return next;
    }
    public int getStreak() {
        return streak;
    }
    public int getHighscore() {
        return highscore;
    }
}
