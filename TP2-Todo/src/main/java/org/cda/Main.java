package org.cda;

import org.cda.layout.TodoLayout;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame("TodoList");
        jFrame.setSize(400,600);
        jFrame.setLocationRelativeTo(null);

        jFrame.add(new TodoLayout());

        jFrame.setVisible(true);

    }
}