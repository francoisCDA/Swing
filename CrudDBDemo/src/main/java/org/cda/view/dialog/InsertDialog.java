package org.cda.view.dialog;

import org.cda.dao.ContactDao;
import org.cda.model.Contact;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsertDialog extends JDialog {



    public InsertDialog() {

        setSize(600,150);

        JLabel labelName = new JLabel("Nom");
        JTextField inputName = new JTextField(20);

        JLabel labelNumber = new JLabel("Numéro");
        JTextField inputNumber = new JTextField(20);

        JButton jButton = new JButton("OK");

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Contact contact = new Contact();
                contact.setName(inputName.getText());
                contact.setNumber(inputNumber.getText());
                ContactDao contactDao = new ContactDao();
                int count = contactDao.addContact(contact);

                if (count>0) {
                    JOptionPane.showConfirmDialog(null,"Operation succeed");
                } else {
                    JOptionPane.showConfirmDialog(null, "Operation failed");
                }
            }
        });



        JPanel jPanel = new JPanel(new FlowLayout());

        jPanel.add(labelName);
        jPanel.add(inputName);
        jPanel.add(labelNumber);
        jPanel.add(inputNumber);
        jPanel.add(jButton);

        add(jPanel);

    }



}
