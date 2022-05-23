package Core;

import java.util.Random;

public class CoinFlip {

    private int streak, highscore;
    private Random random;
    private User user;

    public CoinFlip(User user) {
        streak = 0;
        highscore = user.getCfScore();
        random = new Random();
    }

    public int flip(int guess) {
        int result = random.nextInt(2);

        if (result == guess) {
            streak++;
        } else {
            if (streak > highscore) {
                highscore = streak;
                // Database save highscore
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
