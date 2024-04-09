package org.swing.component;

import lombok.Data;

import javax.swing.*;
import java.awt.*;

public class JlistDemo extends JFrame {

    public JlistDemo() {
        String[] listItems = {"item 1","item 2","item 3","item 4"};

        JList<String> list = new JList<>(listItems);

        JLabel jLabel = new JLabel("Aucun élément sélectionné");

        list.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedItem = list.getSelectedValue();
                jLabel.setText("Element selectionné " + selectedItem);
            }
        });

        this.getContentPane().add(new JScrollPane(list), BorderLayout.CENTER);
        this.getContentPane().add(jLabel, BorderLayout.SOUTH);

        this.setSize(300,200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater( () -> {
            JlistDemo jlistDemo =new JlistDemo();
            jlistDemo.setVisible(true);
        });
    }



}
