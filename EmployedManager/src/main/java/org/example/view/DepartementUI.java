package org.example.view;

import org.example.model.Departement;
import org.example.service.DepartementService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DepartementUI extends JFrame {

    private final DepartementService service = new DepartementService();

    private DefaultListModel<Departement> departementListModel;

    private JDialog dialog;

    public DepartementUI() {
        setTitle("Gestion des Départements");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());


        departementListModel = new DefaultListModel<>();

        updDept();

        JList<Departement> listDepartement = new JList<>(departementListModel);



        JPanel buttonPanel = new JPanel();

        JButton addButton = new JButton("Ajouter");
        JButton removeButton = new JButton("Supprimer");
        JButton editButton = new JButton("Edit");
        JButton salariesButton = new JButton("Salariés");


        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog = new JDialog();

                dialog.setSize(300, 200);
                dialog.setLocationRelativeTo(null);
                dialog.setModal(true);
                dialog.setTitle("Ajouter un departement");

                JLabel labelName = new JLabel("Nom du département");
                JTextField inputName = new JTextField(20);
                JPanel inputPanel = new JPanel();
                inputPanel.add(inputName);

                JButton okButton = new JButton("OK");
                JButton cancelButton = new JButton("Annuler");

                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String name = inputName.getText();
                        Departement departement = new Departement();
                        departement.setName(name);
                        departement.setSalaries(new ArrayList<>());
                        service.save(departement);
                        updDept();
                        dialog.dispose();
                    }
                });


                cancelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dialog.dispose();
                    }
                });


                JPanel buttonPanel = new JPanel();
                buttonPanel.setLayout(new FlowLayout());
                buttonPanel.add(okButton);
                buttonPanel.add(cancelButton);

                dialog.getContentPane().add(labelName, BorderLayout.NORTH);
                dialog.getContentPane().add(inputPanel, BorderLayout.CENTER);
                dialog.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

                dialog.setVisible(true);
            }

        });


        salariesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SalarieUI().setVisible(true);
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(editButton);
        buttonPanel.add(salariesButton);
        add(buttonPanel, BorderLayout.SOUTH);
        add(new JScrollPane(listDepartement), BorderLayout.CENTER);

    }


    private void updDept(){
        List<Departement> deptList = service.getAllDept();
        departementListModel.clear();
        for (Departement dept : deptList) {
            departementListModel.addElement(dept);
        }
    }

}
