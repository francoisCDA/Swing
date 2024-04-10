package org.cda.view.dialog;

import org.cda.dao.ContactDao;
import org.cda.model.Contact;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DisplayDialog extends JDialog {


    public DisplayDialog() {

        setSize(200,500);
        setTitle("Liste des contacts");

        ContactDao contactDao = new ContactDao();

        List<Contact> contacts = contactDao.getAllContact();

        String[][] rows = new String[contacts.size()][3];

        for (int i = 0 ; i < rows.length ; i ++) {
            rows[i] = contacts.get(i).getRow();
        }

        String colum[] = {"ID","NAME","NUMBER"};

        JTable tb = new JTable(rows,colum);

        tb.setBounds(30,40,200,200);

        JScrollPane scrollPane = new JScrollPane(tb);

        add(scrollPane);

    }


}
