package org.swing;

import org.swing.layout.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame jFrame = new JFrame("Demo Swing Layout");
        jFrame.setSize(600,450);
        jFrame.setLocationRelativeTo(null);
        //jFrame.setResizable(false);

        //jFrame.pack();

        //jFrame.add(new BorderLayoutDemo());
        //jFrame.add(new FlowLayoutDemo().getJPanel());
        //jFrame.add(new GridLayoutDemo().getJPanel());
        //jFrame.add(new GridBagLayoutDemo().getJPanel());

        jFrame.add(new BoxLayoutDemo().getPanel());


        jFrame.setVisible(true);


    }
}