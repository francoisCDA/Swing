package org.cda.layout;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.text.AttributedString;

public class TodoLayout extends JPanel {

    private JTextField inputText;

    private DefaultListModel<String> listItems;
    private JList<String> todoList;


    private Integer selectedTodoIdx;

    private JPanel buttonsPanel;


    public TodoLayout() {

        // input
        setLayout(new BorderLayout());

        inputText = new JTextField();

        add(inputText,BorderLayout.NORTH);

        // list

        listItems = new DefaultListModel<>();

        todoList = new JList<>(listItems);

        todoList.addListSelectionListener(e -> {
            if(!e.getValueIsAdjusting()) {
                selectedTodoIdx = todoList.getSelectedIndex();
            }
        });

        add(todoList,BorderLayout.CENTER);

        //buttons

        buttonsPanel = new JPanel(new FlowLayout());

        ImageIcon icone = new ImageIcon("src/main/resources/icone/plus.png");
        Image image = icone.getImage();
        Image newimg = image.getScaledInstance(22,22, Image.SCALE_SMOOTH);
        icone = new ImageIcon(newimg);
        JButton addButton = new JButton("Ajouter" ,icone);
        addButton.addActionListener(e -> {
            listItems.addElement(inputText.getText());
        });

        icone = new ImageIcon("src/main/resources/icone/supprimer.png");
        image = icone.getImage();
        newimg =  image.getScaledInstance(22,22, Image.SCALE_SMOOTH);
        icone = new ImageIcon(newimg);
        JButton deleteButton = new JButton("Delete",icone);
        deleteButton.addActionListener(e -> {
            if (selectedTodoIdx != null) {
                listItems.remove(selectedTodoIdx);
                selectedTodoIdx = null;
            }

        });

        icone = new ImageIcon("src/main/resources/icone/completer.png");
        image = icone.getImage();
        newimg =  image.getScaledInstance(22,22, Image.SCALE_SMOOTH);
        icone = new ImageIcon(newimg);
        JButton completeButton = new JButton("Complete",icone);
        completeButton.addActionListener(e -> {
            if (selectedTodoIdx != null) {
                String task = "<html><strike>" + listItems.get(selectedTodoIdx) + "</strike></html>";
                listItems.set(selectedTodoIdx,task);
                selectedTodoIdx = null;
            }
        });

        buttonsPanel.add(addButton);
        buttonsPanel.add(deleteButton);
        buttonsPanel.add(completeButton);

        add(buttonsPanel,BorderLayout.SOUTH);



    }

}
