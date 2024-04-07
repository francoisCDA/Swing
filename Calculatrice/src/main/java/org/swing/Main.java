package org.swing;

import org.swing.panelComponents.MainContainerLayout;

import javax.swing.*;

public class Main {



    public static void main(String[] args) {

        JFrame jFrame = new JFrame("Calculatrice CDA");
        jFrame.setSize(400,600);
        jFrame.setLocationRelativeTo(null);

        jFrame.add(new MainContainerLayout());

        jFrame.setVisible(true);



    }
}