package org.cda.view.dialog;

import org.cda.dao.ContactDao;
import org.cda.model.Contact;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateDialog extends JDialog {


    private JLabel labelId = new JLabel("ID");
    private JComboBox<Integer> comboIds;

    private JButton searchBtn = new JButton("search");;

    private JLabel labelName = new JLabel("Nom");
    private JTextField inputName = new JTextField(20);

    private JLabel labelNumber = new JLabel("Numéro");
    private JTextField inputNumber = new JTextField(20);

    private JButton updBtn = new JButton("Update");

    private JButton cancelBtn = new JButton("Cancel");

    private JPanel contentPanel;

    public UpdateDialog() {
        setTitle("Update");
        setBounds(50,50,400, 400);
        getContentPane().setLayout(new BorderLayout());

        ContactDao contactDao = new ContactDao();

        contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setBorder(new EmptyBorder(5,5,5,5));

        getContentPane().add(contentPanel,BorderLayout.CENTER);

        comboIds = new JComboBox<>(contactDao.getUsersIds());


        labelId.setBounds(10,50,50,20);
        comboIds.setBounds(80,50,50,20);
        searchBtn.setBounds(150,50,100,20);

        labelName.setBounds(10,100,80,20);
        labelNumber.setBounds(10, 150,80,20);

        inputName.setBounds(80,100,80,20);
        inputNumber.setBounds(80,150,80,20);

        updBtn.setBounds(10,200,100,20);
        cancelBtn.setBounds(120,200,100,20);


        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int targetId = (int) comboIds.getSelectedItem();
                Contact contact = contactDao.getContactById(targetId);
                inputName.setText(contact.getName());
                inputNumber.setText(contact.getNumber());
            }
        });

        updBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Contact updContact = new Contact();
                int targetId = (int) comboIds.getSelectedItem();
                updContact.setId(targetId);
                updContact.setName(inputName.getText());
                updContact.setNumber(inputNumber.getText());

                contactDao.updContact(updContact);

                dispose();
            }
        });


        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });


        contentPanel.add(labelId);
        contentPanel.add(comboIds);
        contentPanel.add(searchBtn);
        contentPanel.add(labelName);
        contentPanel.add(labelNumber);
        contentPanel.add(inputName);
        contentPanel.add(inputNumber);
        contentPanel.add(updBtn);
        contentPanel.add(cancelBtn);



    }


}
