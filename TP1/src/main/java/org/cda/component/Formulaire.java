package org.cda.component;

import org.cda.service.UserService;

import javax.swing.*;
import java.awt.*;

public class Formulaire extends JPanel {

    private final UserService userService = UserService.getUserService();

    private JLabel nameLabel = new JLabel("Nom");
    private JLabel emailLabel = new JLabel("Email");
    private JLabel genderLabel = new JLabel("Genre");
    private JTextField nameInput = new JTextField();
    private JTextField emailInput = new JTextField();
    private JRadioButton manRadio = new JRadioButton("Homme");
    private JRadioButton womanRadio = new JRadioButton("Femme");
    private JButton addButton = new JButton("Ajouter");

    private GridBagConstraints constraints = new GridBagConstraints();

    public Formulaire() {

        setLayout(new GridBagLayout());

        constraints.fill = GridBagConstraints.BOTH;

        constraints.gridx = 0 ;
        constraints.gridy = 0 ;
        constraints.gridwidth = 1;
        constraints.gridheight =1;
      //  constraints.weightx = 1 ;
        add(nameLabel,constraints);

        constraints.gridx = 1 ;
        constraints.gridy = 0 ;
        constraints.gridwidth = 2;
        constraints.gridheight =1;
      //  constraints.weightx = 2 ;
        add(nameInput,constraints);

        constraints.gridx = 0 ;
        constraints.gridy = 1 ;
        constraints.gridwidth = 1;
        constraints.gridheight =1;
       // constraints.weightx = 1 ;
        add(emailLabel,constraints);

        constraints.gridx = 1 ;
        constraints.gridy = 1 ;
        constraints.gridwidth = 2;
        constraints.gridheight =1;
      //  constraints.weightx = 2 ;
        add(emailInput,constraints);

        constraints.gridx = 0 ;
        constraints.gridy = 2 ;
        constraints.gridwidth = 1;
        constraints.gridheight =1;
      //  constraints.weightx = 1 ;
        add(genderLabel,constraints);

        constraints.gridx = 1 ;
        constraints.gridy = 2 ;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        manRadio.setSelected(true);
        manRadio.addActionListener(e -> {
            manRadio.setSelected(true);
            womanRadio.setSelected(false);
        });

        add(manRadio,constraints);

        constraints.gridx = 2 ;
        constraints.gridy = 2 ;
        constraints.gridwidth = 1;
        constraints.gridheight =1;
        womanRadio.addActionListener(e ->{
            manRadio.setSelected(false);
            womanRadio.setSelected(true);
        });
        add(womanRadio,constraints);

        constraints.gridx = 1 ;
        constraints.gridy = 3 ;
        constraints.gridwidth = 1;
        constraints.gridheight =1;
       // constraints.weightx = 2 ;

        addButton.addActionListener(e -> saveUser(nameInput.getText(), emailInput.getText(), manRadio.isSelected()));

        add(addButton,constraints);

    }

    private void saveUser(String name, String email, boolean isMan) {

        String genre = isMan ? "homme" : "femme";
        System.out.println("nom :" + name +  ", email : " +email+ ", genre : " + genre);

        userService.save(name,email,genre);

        UserTable.update();

    }


}
