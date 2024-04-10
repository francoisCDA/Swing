package org.example.view;

import org.example.model.Salarie;
import org.example.service.SalarieService;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SalarieUI extends JFrame {

    private final SalarieService salarieService = new SalarieService();

    public SalarieUI() {
        setTitle("Gestion des Salariés");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());


        String[][] salarieList = salarieService.getSalariesRows();
        String[] header = {"ID","Lastname","Firstname","Role","Departement"};

        JTable salarieTable = new JTable(salarieList, header);


        JPanel buttonPanel = new JPanel();

        buttonPanel.setLayout(new FlowLayout());

        JButton addButton = new JButton("Ajouter");
        JButton removeButton = new JButton("Supprimer");
        JButton editButton = new JButton("Edit");
        JButton departementButton = new JButton("Departement");



        departementButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new DepartementUI().setVisible(true);
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(editButton);
        buttonPanel.add(departementButton);


        add(new JScrollPane(salarieTable), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);


    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SalarieUI().setVisible(true);
            }
        });
    }

}
