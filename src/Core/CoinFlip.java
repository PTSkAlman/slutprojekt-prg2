package Core;

import java.util.Random;

public class CoinFlip {

    private int streak, highscore;

    public CoinFlip() {
        streak = 0;
        highscore = 0;
    }

    public int flip(int guess) {
        Random random = new Random();
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
