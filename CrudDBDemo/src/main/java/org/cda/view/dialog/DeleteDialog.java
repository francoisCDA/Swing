package org.cda.view.dialog;

import org.cda.dao.ContactDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteDialog extends JDialog {

    public DeleteDialog() {

        setSize(200,200);
        setTitle("Supprimer");

        ContactDao contactDao = new ContactDao();

        JComboBox<Integer> comboId = new JComboBox<>(contactDao.getUsersIds());

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int target = (int) comboId.getSelectedItem();
                int count = contactDao.rmUserById(target);

                dispose();
            }
        });


        JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));


        jPanel.add(comboId);

        jPanel.add(deleteButton);

        add(jPanel);

    }

}
