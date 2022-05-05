package Core;

import java.util.Random;

public class CoinFlip {
    public CoinFlip() {

    }

    public int flip(int guess) {
        Random random = new Random();
        int result = random.nextInt(2);

        if (result == guess) {
            System.out.println("Correct");
        } else {
            System.out.println("Wrong");
        }

        return result;
    }
}
