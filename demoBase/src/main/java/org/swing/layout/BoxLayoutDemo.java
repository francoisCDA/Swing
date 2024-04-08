package org.swing.layout;

import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public class BoxLayoutDemo  {

    private JPanel panel;

    private JPanel messagePanel;


    public BoxLayoutDemo() {



        panel = new JPanel();

        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

        messagePanel = new JPanel();

        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));

        JButton addButton = new JButton("Ajouter message");
        addButton.addActionListener(e -> addmessage("Ajouter une message"));

        addButton.setBackground(Color.green);

        JButton removeLastButton = new JButton("Supprimer le dernier message");
        removeLastButton.addActionListener(e -> removeLastMessage());

        removeLastButton.setBackground(Color.RED);

        JButton clearButton = new JButton("Effacer tous les messages");
        clearButton.addActionListener(e -> cleareMessages());

        clearButton.setBackground(Color.BLACK);
        clearButton.setForeground(Color.WHITE);

        panel.add(addButton);
        panel.add(removeLastButton);
        panel.add(clearButton);

        panel.add(new JScrollPane(messagePanel));

    }

    private void addmessage(String message) {
        messagePanel.add(new JLabel(message));
        updateUI();
    }

    private void removeLastMessage() {
        int componenetCount = messagePanel.getComponentCount();

        if (componenetCount > 0) {
            messagePanel.remove(componenetCount-1);
        }
        updateUI();
    }

    private void cleareMessages() {
        messagePanel.removeAll();
        updateUI();
    }

    private void updateUI(){

        messagePanel.revalidate();
        messagePanel.repaint();

    }

}
