import Core.CoinFlip;
import UI.View;

import javax.swing.*;

public class Controller {

    public Controller() {
        View view = new View("Game");
        view.init();

        view.getCoinFlip().addActionListener(actionEvent -> {
            CoinFlip cf = new CoinFlip();
            view.newFlipGame();
            SwingUtilities.updateComponentTreeUI(view);
            view.getFlipButton().addActionListener(actionEvent1 -> cf.flip(view.getGuess()));
        });

        view.getHigherOrLower().addActionListener(actionEvent -> {

        });
    }

    public static void main(String[] args) {
        Controller c = new Controller();
    }
}
