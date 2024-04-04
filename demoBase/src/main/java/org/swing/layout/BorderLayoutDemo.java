package org.swing.layout;

import javax.swing.*;
import java.awt.*;

public class BorderLayoutDemo extends JPanel {

    private JButton buttonA;

    private JButton buttonB;

    private JButton buttonC;

    private JButton buttonD;

    private JButton buttonE;

    public BorderLayoutDemo() {
        setLayout(new BorderLayout());
        buttonA = new JButton("NORTH");
        buttonB = new JButton("CENTER");
        buttonC = new JButton("SOUTH");
        buttonD = new JButton("EST");
        buttonE = new JButton("WEST");

        buttonA.setForeground(Color.BLUE);
        buttonB.setForeground(Color.CYAN);
        buttonC.setForeground(Color.orange);
        buttonD.setForeground(Color.BLACK);
        buttonE.setForeground(Color.GREEN);

        add(buttonA,BorderLayout.NORTH);
        add(buttonB,BorderLayout.CENTER);
        add(buttonC,BorderLayout.SOUTH);
        add(buttonD,BorderLayout.EAST);
        add(buttonE,BorderLayout.WEST);

    }
}
