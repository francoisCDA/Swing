package org.cda.layout;

import org.cda.component.Formulaire;
import org.cda.component.UserTable;

import javax.swing.*;
import java.awt.*;

public class MainLayout extends JPanel {

    public MainLayout() {

        setLayout(new BorderLayout());

        add(new Formulaire(),BorderLayout.NORTH);
        add(UserTable.getUserTable().getScrollPane(),BorderLayout.CENTER);

    }

}
