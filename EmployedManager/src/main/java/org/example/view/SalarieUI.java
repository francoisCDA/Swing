package org.example.view;

import org.example.model.Departement;
import org.example.model.Role;
import org.example.model.Salarie;
import org.example.service.DepartementService;
import org.example.service.SalarieService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SalarieUI extends JFrame {

    private final SalarieService salarieService = new SalarieService();
    private final DepartementService departementService = new DepartementService();

    private DefaultTableModel tableModel;
    private JTable salarieTable;

    private JDialog dialog;

    public SalarieUI() {
        setTitle("Gestion des Salariés");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        String[] header = {"ID","Lastname","Firstname","Role","Departement"};

        tableModel = new DefaultTableModel(header,0);

        salarieTable = new JTable(tableModel);

        updSalariesList();

        JPanel buttonPanel = new JPanel();

        buttonPanel.setLayout(new FlowLayout());

        JButton addButton = new JButton("Ajouter");
        JButton removeButton = new JButton("Supprimer");
        JButton editButton = new JButton("Edit");
        JButton departementButton = new JButton("Département");



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


        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog = new JDialog();

                dialog.setSize(500, 400);
                dialog.setLocationRelativeTo(null);
                dialog.setModal(true);
                dialog.setTitle("Ajouter un Salarié");

                JLabel firstNameLabel = new JLabel("Nom: ");
                JLabel lastNameLabel = new JLabel("Prenom: ");
                JLabel roleLabel = new JLabel("Role: ");
                JLabel departementLabel = new JLabel("Departement: ");

                JTextField firstNameField = new JTextField(20);
                JTextField lastNameField = new JTextField(20);

                ButtonGroup radioRoles = new ButtonGroup();
                List<JRadioButton> radioButtonList = new ArrayList<>();

                JPanel centerPanel = new JPanel();
                centerPanel.setLayout(null);
                centerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

                int x = 0;
                for (Role ro :Role.values()) {
                    JRadioButton radioButton = new JRadioButton(ro.toString());
                    x += 100;
                    radioButton.setBounds(x, 125, 90,50 );
                    centerPanel.add(radioButton);
                    radioRoles.add(radioButton);
                    radioButtonList.add(radioButton);
                }

                radioButtonList.get(0).setSelected(true);

                JComboBox<String> departementNames = new JComboBox<>(departementService.getDeptName());

                JButton addButtonModal = new JButton("Ajouter");
                JButton cancelButtonModal = new JButton("Annuler");

                addButtonModal.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        String role = "";

                        for (JRadioButton jra : radioButtonList ) {
                            if (jra.isSelected()) {
                                role = jra.getText();
                            }
                        }

                        String deptName = (String) departementNames.getSelectedItem();

                        Departement dept = departementService.getDeptByName(deptName);

                        Salarie salarie = Salarie.builder()
                                .firstname(firstNameField.getText())
                                .lastname(lastNameField.getText())
                                .role(Role.valueOf(role))
                                .departement(dept)
                                .build();

                        salarieService.save(salarie);
                        updSalariesList();
                        dialog.dispose();
                    }
                });

                cancelButtonModal.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dialog.dispose();
                    }
                });

                firstNameLabel.setBounds(10,50,100,20);
                lastNameLabel.setBounds(10,100,100,20);
                roleLabel.setBounds(10,150,100,20);
                departementLabel.setBounds(10,200,100,20);

                firstNameField.setBounds(100,50,200,20);
                lastNameField.setBounds(100,100,200,20);
                departementNames.setBounds(100,200,200,20);


                centerPanel.add(firstNameLabel);
                centerPanel.add(lastNameLabel);
                centerPanel.add(firstNameField);
                centerPanel.add(lastNameField);
                centerPanel.add(roleLabel);
                centerPanel.add(lastNameField);
                centerPanel.add(departementLabel);
                centerPanel.add(departementNames);


                JPanel buttonModal = new JPanel();
                buttonModal.setLayout(new FlowLayout());
                buttonModal.add(addButtonModal);
                buttonModal.add(cancelButtonModal);

                dialog.getContentPane().add(buttonModal, BorderLayout.SOUTH);
                dialog.getContentPane().add(centerPanel, BorderLayout.CENTER);

                dialog.setVisible(true);
            }
        });

        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int target = salarieTable.getSelectedRow();
                String idString =  (String) salarieTable.getValueAt(target,0);
                salarieService.rmSalarieById(idString);
                updSalariesList();
            }
        });

        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int target = salarieTable.getSelectedRow();
                String deptName = (String) salarieTable.getValueAt(target,4);
                Departement dept = departementService.getDeptByName(deptName);

            }
        });

        add(new JScrollPane(salarieTable), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

    }

    private void updSalariesList() {

        String[][] salarieList = salarieService.getSalariesRows();
        tableModel.setRowCount(0);
        for (int i = 0; i < salarieList.length; i++) {
            tableModel.addRow(salarieList[i]);
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SalarieUI().setVisible(true);
            }
        });
    }

}
