package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame {

    private JMenuBar menuBar;
    private JMenu gameMenu;
    private JMenuItem coinFlip, higherOrLower;

    private JPanel panel;
    private JButton guessButton;
    private JToggleButton heads, tails, higher, lower;
    private ButtonGroup buttonGroup;
    private JTextField current, next;

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
        clearFrame();
        panel = new JPanel();
        guessButton = new JButton("Guess");
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

        panel.add(guessButton);

        this.add(panel);
    }

    public void newHigherOrLowerGame() {
        clearFrame();
        panel = new JPanel();
        guessButton = new JButton("Guess");
        current = new JTextField();
        next = new JTextField();
        buttonGroup = new ButtonGroup();
        higher = new JToggleButton("Higher");
        lower = new JToggleButton("Lower");
        ActionListener listener = actionEvent -> {
            if (actionEvent.getActionCommand().equals("Higher")) {
                guess = 1;
            } else if (actionEvent.getActionCommand().equals("Lower")) {
                guess = 0;
            }
        };
        higher.addActionListener(listener);
        lower.addActionListener(listener);
        buttonGroup.add(higher);
        buttonGroup.add(lower);
        panel.add(higher);
        panel.add(lower);

        panel.add(guessButton);

        current.setEditable(false);
        next.setEditable(false);
        panel.add(current);
        panel.add(next);

        this.add(panel);
    }

    private void clearFrame() {
        this.getContentPane().removeAll();
        this.repaint();
    }

    // Getters & Setters
    public JButton getGuessButton() {
        return guessButton;
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
    public void setCurrentNumber(int num) {
        current.setText(num+"");
    }
    public void setNextNumber(int num) {
        next.setText(num+"");
    }
}
