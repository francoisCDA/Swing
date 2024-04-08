package org.swing;

import org.swing.layout.TaquinLayout;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame jFrame = new JFrame("Jeu de Taquin");

        jFrame.setSize(600,600);
        jFrame.setLocationRelativeTo(null);

        jFrame.add(new TaquinLayout(3).getPanel());

        jFrame.setVisible(true);
    }
}