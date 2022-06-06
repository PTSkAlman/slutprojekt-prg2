import Core.CoinFlip;
import Core.DatabaseConnector;
import Core.HigherOrLower;
import Core.User;
import UI.Login;
import UI.View;

import javax.swing.*;

public class Controller {

    private User user;
    private DatabaseConnector dbc;
    private View view;

    public Controller() {
        dbc = new DatabaseConnector();
        Login login = new Login();
        login.getLogin().addActionListener(actionEvent -> {
            if (dbc.login(login.getUsername(), login.getPassword()) != null) {
                user = dbc.login(login.getUsername(), login.getPassword());
                view = new View("Game", user);
                login.dispose();
                view.init();
                viewListeners(view);
            }
        });
        login.getCreate().addActionListener(actionEvent -> {
            if (login.getUsername().length() >= 3 && login.getPassword().length() >= 8) {
                dbc.register(login.getUsername(), login.getPassword());
            }
        });
    }

    private void viewListeners(View view) {
        view.getCoinFlip().addActionListener(actionEvent -> {
            CoinFlip cf = new CoinFlip(user, dbc);
            view.setHighscore(user.getCfScore());
            view.newFlipGame();
            SwingUtilities.updateComponentTreeUI(view);
            view.getGuessButton().addActionListener(actionEvent1 -> {
                view.setHighscore(user.getCfScore());
                cf.flip(view.getGuess());
                view.setStreak(cf.getStreak());
                SwingUtilities.updateComponentTreeUI(view);
            });
        });

        view.getHigherOrLower().addActionListener(actionEvent -> {
            HigherOrLower hl = new HigherOrLower(user, dbc);
            view.setHighscore(user.getHlScore());
            view.newHigherOrLowerGame();
            view.setCurrentNumber(hl.getCurrent());
            SwingUtilities.updateComponentTreeUI(view);
            view.getGuessButton().addActionListener(actionEvent1 -> {
                view.setHighscore(user.getHlScore());
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
