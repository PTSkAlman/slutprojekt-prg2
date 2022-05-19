package Core;

import java.util.Random;

public class HigherOrLower {

    private int streak, highscore, current, next;
    private Random random;

    public HigherOrLower() {
        streak = 0;
        highscore = 0;
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
                highscore = streak;
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
