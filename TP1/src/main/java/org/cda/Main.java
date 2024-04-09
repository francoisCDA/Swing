package org.cda;

import org.cda.layout.MainLayout;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame jFrame = new JFrame("User List");
        jFrame.setSize(400,600);
        jFrame.setLocationRelativeTo(null);

        jFrame.add(new MainLayout());

        jFrame.setVisible(true);


    }
}