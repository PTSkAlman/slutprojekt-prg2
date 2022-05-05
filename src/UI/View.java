package UI;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {

    private JPanel panel;
    private JButton flip;
    private JToggleButton heads, tails;
    private ButtonGroup buttonGroup;

    public View(String title) {
        this.setTitle(title);
    }

    public void init() {
        panel = new JPanel();
        flip = new JButton("Flip");
        buttonGroup = new ButtonGroup();
        heads = new JToggleButton("Heads");
        tails = new JToggleButton("Tails");
        buttonGroup.add(heads);
        buttonGroup.add(tails);

        panel.add(flip);
        panel.add(heads);
        panel.add(tails);

        this.add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(800,800));
        this.setVisible(true);
        this.pack();
    }

}
