package org.swing.layout;

import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public class FlowLayoutDemo {

    private JPanel jPanel;

    private JLabel resultLabel;

    private JComboBox<String> comboBox;

    public FlowLayoutDemo(){

        jPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,20));

        JButton button = new JButton("Button1");
        button.setBackground(Color.MAGENTA);
        button.addActionListener(e -> System.out.printf("Button1 click"));
        jPanel.add(button);

        jPanel.add(new JButton("Button2"));
        jPanel.add(new JButton("Button3"));

        jPanel.add(new JLabel("Label"));

        JTextField input = new JTextField(20);
        jPanel.add(input);

        comboBox = new JComboBox<>(new String[]{"option1","option2","option3"});
        jPanel.add(comboBox);

        resultLabel = new JLabel("Résultat et Selection s'affichent ici");
        jPanel.add(resultLabel);

        JButton validateButton = new JButton("Valider");

        validateButton.addActionListener(e -> {
            String selected = (String) comboBox.getSelectedItem();
            String inputText = input.getText();

            resultLabel.setText("Input Data : " + inputText + "Selected Data : " + selected);
        });

        jPanel.add(validateButton);

    }


}
