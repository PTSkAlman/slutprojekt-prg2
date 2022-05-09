package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame {

    private JMenuBar menuBar;
    private JMenu gameMenu;
    private JMenuItem coinFlip, higherOrLower;

    private JPanel panel;
    private JButton flipButton;
    private JToggleButton heads, tails;
    private ButtonGroup buttonGroup;

    private int guess;

    public View(String title) {
        this.setTitle(title);
    }

    public void init() {
        menuBar = new JMenuBar();
        gameMenu = new JMenu("Games");
        coinFlip = new JMenuItem("Coin Flip");
        higherOrLower = new JMenuItem("Higher or Lower");

        gameMenu.add(coinFlip);
        gameMenu.add(higherOrLower);

        menuBar.add(gameMenu);

        this.setJMenuBar(menuBar);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(800,800));
        this.setVisible(true);
        this.pack();
    }

    public void newFlipGame() {
        panel = new JPanel();
        flipButton = new JButton("Flip");
        buttonGroup = new ButtonGroup();
        heads = new JToggleButton("Heads");
        tails = new JToggleButton("Tails");
        ActionListener listener = actionEvent -> {
            if (actionEvent.getActionCommand().equals("Heads")) {
                guess = 0;
            } else if (actionEvent.getActionCommand().equals("Tails")) {
                guess = 1;
            }
        };
        heads.addActionListener(listener);
        tails.addActionListener(listener);
        buttonGroup.add(heads);
        buttonGroup.add(tails);
        panel.add(heads);
        panel.add(tails);

        panel.add(flipButton);

        this.add(panel);
    }

    // Getters
    public JButton getFlipButton() {
        return flipButton;
    }
    public int getGuess() {
        return guess;
    }
    public JMenuItem getCoinFlip() {
        return coinFlip;
    }
    public JMenuItem getHigherOrLower() {
        return higherOrLower;
    }
}
