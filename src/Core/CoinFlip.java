package Core;

import java.util.Random;

public class CoinFlip {

    private int streak;

    public CoinFlip() {
        streak = 0;
    }

    public int flip(int guess) {
        Random random = new Random();
        int result = random.nextInt(2);

        if (result == guess) {
            streak++;
        } else {
            streak = 0;
        }
        System.out.println(streak);

        return result;
    }
}
