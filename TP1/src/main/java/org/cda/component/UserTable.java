package org.cda.component;

import org.cda.service.UserService;

import javax.swing.*;
import java.awt.*;

public class UserTable extends JPanel {

    private static UserTable instance;

    private static UserService userService = UserService.getUserService();

    private JScrollPane scrollPane;
    private JTable table;

    private String[][] rows;

    private final String[] colum = {"Name","Email","Genre"};

    private UserTable() {

        rows = userService.getUsers();

        table = new JTable(rows,colum);

        table.setBounds(30,40,200,300);

        scrollPane = new JScrollPane(table);

    }

    public static void update() {
        instance = new UserTable();
        instance.revalidate();
        instance.repaint();
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public static UserTable getUserTable() {
        if (instance == null) {
            instance = new UserTable();
        }
        return instance;
    }


}
