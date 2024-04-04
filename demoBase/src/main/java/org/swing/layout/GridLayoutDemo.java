package org.swing.layout;

import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public class GridLayoutDemo {

    private JPanel jPanel;

    public GridLayoutDemo() {
        jPanel = new JPanel(new GridLayout(3,3));

        for ( int i = 1 ; i < 10 ; i++ ) {

            JButton jButton = new JButton("Button " + i);

            jButton.addActionListener((e) -> {
                System.out.printf(((JButton)(e.getSource())).getText() + " ");
            });

            jPanel.add(jButton);
        }

    }



}
