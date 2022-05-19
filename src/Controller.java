import Core.CoinFlip;
import Core.DatabaseConnector;
import Core.HigherOrLower;
import UI.View;

import javax.swing.*;

public class Controller {

    public Controller() {
        DatabaseConnector dbc = new DatabaseConnector();
        dbc.login();
        View view = new View("Game");
        view.init();
        viewListeners(view);
    }

    private void viewListeners(View view) {
        view.getCoinFlip().addActionListener(actionEvent -> {
            CoinFlip cf = new CoinFlip();
            view.newFlipGame();
            SwingUtilities.updateComponentTreeUI(view);
            view.getGuessButton().addActionListener(actionEvent1 -> {
                cf.flip(view.getGuess());
                view.setStreak(cf.getStreak());
                SwingUtilities.updateComponentTreeUI(view);
            });
        });

        view.getHigherOrLower().addActionListener(actionEvent -> {
            HigherOrLower hl = new HigherOrLower();
            view.newHigherOrLowerGame();
            view.setCurrentNumber(hl.getCurrent());
            SwingUtilities.updateComponentTreeUI(view);
            view.getGuessButton().addActionListener(actionEvent1 -> {
                view.setCurrentNumber(hl.getCurrent());
                hl.guess(view.getGuess());
                view.setNextNumber(hl.getNext());
                view.setStreak(hl.getStreak());
                SwingUtilities.updateComponentTreeUI(view);
            });
        });
    }

    public static void main(String[] args) {
        Controller c = new Controller();
    }
}
