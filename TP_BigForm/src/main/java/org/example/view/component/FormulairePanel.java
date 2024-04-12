package org.example.view.component;

import com.toedter.calendar.JDateChooser;
import org.example.model.Employee;
import org.example.model.Qualification;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

public class FormulairePanel extends JPanel {

    private Employee employee;

    public Employee getEmployee() {return employee;}

    public FormulairePanel(Employee employee, Border border) {
        this.employee = employee;
        setBorder(border);

        setLayout(new BorderLayout());

        // WEST

        JPanel westPanel = new JPanel();
        westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));

           JPanel idPanel = new JPanel();
                idPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
                        JLabel idLabel = new JLabel("ID");
                        idLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
                    idPanel.add(idLabel);
                        JTextField idField = new JTextField(10);
                            idField.setEditable(false);
                            idField.setFont(new Font("Tahoma", Font.PLAIN, 12));
//                            idField.getDocument().addDocumentListener(new DocumentListener() {
//
//                                @Override
//                                public void insertUpdate(DocumentEvent e) {
//                                    updTextField();
//                                }
//
//                                @Override
//                                public void removeUpdate(DocumentEvent e) {
//                                    updTextField();
//                                }
//
//                                @Override
//                                public void changedUpdate(DocumentEvent e) {
//                                    updTextField();
//                                }
//
//                                private void updTextField() {
//                                    idField.setText(employee.getId().toString());
//                                }
//                            });

                    idPanel.add(idField);
            idPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
            westPanel.add(idPanel);

            JPanel namePanel = new JPanel();
                namePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
                    JLabel nameLabel = new JLabel("Nom");
                    nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
                namePanel.add(nameLabel);
                    JTextField nameField = new JTextField(10);
                      nameField.getDocument().addDocumentListener(new DocumentListener() {

                          @Override
                          public void insertUpdate(DocumentEvent e) {
                              updateEmployeeName();
                          }

                          @Override
                          public void removeUpdate(DocumentEvent e) {
                              updateEmployeeName();
                          }

                          @Override
                          public void changedUpdate(DocumentEvent e) {
                              updateEmployeeName();
                          }
                          private void updateEmployeeName() {
                              String name = nameField.getText();
                              employee.setNameNoEvent(name);
                          }
                      });
                      employee.addPropertyChangeListener("name", new PropertyChangeListener() {
                          @Override
                          public void propertyChange(PropertyChangeEvent evt) {
                                nameField.setText(employee.getName());
                          }
                      });

                namePanel.add(nameField);
            namePanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
            westPanel.add(namePanel);

            // Gender radio
            JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                    JLabel genderLabel = new JLabel("Gender");
                    genderLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
                    ButtonGroup buttonGroup = new ButtonGroup();
                    JRadioButton radioMale = new JRadioButton("Male");
                    JRadioButton radioFemale = new JRadioButton("Female");
                        if (employee != null) {
                            if (employee.isFemale()) {
                                radioFemale.setSelected(true);
                            } else {
                                radioMale.setSelected(true);
                            }
                        }
                    buttonGroup.add(radioMale);
                    buttonGroup.add(radioFemale);
                    genderPanel.add(genderLabel);
                    genderPanel.add(radioMale);
                    genderPanel.add(radioFemale);
            genderPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
            westPanel.add(genderPanel);

            JPanel agePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                JLabel ageLabel = new JLabel("Age");
                ageLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
                agePanel.add(ageLabel);
                JTextField ageField = new JTextField(10);
                    ageField.getDocument().addDocumentListener(new DocumentListener() {

                        @Override
                        public void insertUpdate(DocumentEvent e) {
                            updateEmployeeName();
                        }

                        @Override
                        public void removeUpdate(DocumentEvent e) {
                            updateEmployeeName();
                        }

                        @Override
                        public void changedUpdate(DocumentEvent e) {
                            updateEmployeeName();
                        }
                        private void updateEmployeeName() {
                            Integer age = Integer.parseInt(ageField.getText());
                            employee.setAge(age);                        }
                    });
                    employee.addPropertyChangeListener("age", new PropertyChangeListener() {
                        @Override
                        public void propertyChange(PropertyChangeEvent evt) {
                            ageField.setText(employee.getAge().toString());
                        }
                    });
                agePanel.add(ageField);
            agePanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
            westPanel.add(agePanel);

            JPanel bloodPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                JLabel bloodLabel = new JLabel("BloodType");
                bloodLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
                bloodPanel.add(bloodLabel);
                JTextField bloodField = new JTextField(10);
                bloodPanel.add(bloodField);
            bloodPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
            westPanel.add(bloodPanel);

            JPanel numberPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                JLabel numberLabel = new JLabel("Phone Number");
                numberLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
                numberPanel.add(numberLabel);
                JTextField numberField = new JTextField(10);
                numberPanel.add(numberField);
            numberPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
            westPanel.add(numberPanel);

            // Qualifications
            JPanel qualifPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                JLabel qualifLabel = new JLabel("Qualification");
                qualifLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
                qualifPanel.add(qualifLabel);
                    String[] qualif = new String[Qualification.values().length];
                    for (int i = 0; i < Qualification.values().length; i++) {
                        qualif[i] = Qualification.values()[i].toString();
                    }
                JComboBox<String> comboBox = new JComboBox<>(qualif);
                qualifPanel.add(comboBox);
            qualifPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
            westPanel.add(qualifPanel);

            // Date
            JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                 JLabel dateLabel = new JLabel("Start date");
                 dateLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
                 datePanel.add(dateLabel);
                 JDateChooser dateChooser = new JDateChooser();
                 dateChooser.setPreferredSize(new Dimension(100, 20));
                 datePanel.add(dateChooser);
            datePanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
            westPanel.add(datePanel);

        add(westPanel, BorderLayout.WEST);


        // CENTER
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

            //address
            JPanel addressPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                 JLabel addressLabel = new JLabel("Address");
                 addressLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
                 addressPanel.add(addressLabel);
                 JTextArea textArea = new JTextArea(10, 25);
                 JScrollPane scrollPane = new JScrollPane(textArea);
                 addressPanel.add(scrollPane);
            addressPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
            centerPanel.add(addressPanel);


            // Bouton de chargement d'image
            JButton photoButton = new JButton("Upload image");
            photoButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
            centerPanel.add(photoButton);

            JPanel pathPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                  JLabel pathLabel = new JLabel("Path image");
                  pathLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
                  pathPanel.add(pathLabel);
                  JTextField pathField = new JTextField(15);
                  pathPanel.add(pathField);
                  pathPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
            centerPanel.add(pathPanel);

    add(centerPanel, BorderLayout.CENTER);


        JPanel estPanel = new JPanel(null);
        estPanel.setLayout(new FlowLayout());
        estPanel.setSize(150,300);
                JPanel photoPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                photoPanel.setPreferredSize(new Dimension(150, 200));
                photoPanel.setBackground(Color.blue);
                photoPanel.setBorder(border);
                JLabel photoLabel = new JLabel("photo");
                photoPanel.add(photoLabel);
        estPanel.add(photoPanel);

    add(estPanel, BorderLayout.EAST);

        photoButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                // Obtenez le fichier sélectionné
                File selectedFile = fileChooser.getSelectedFile();
                // Afficher l'image sélectionnée
                ImageIcon imageIcon = new ImageIcon(selectedFile.getAbsolutePath());
                photoLabel.setIcon(imageIcon);
            }
        });

//        employee.addPropertyChangeListener(new PropertyChangeListener() {
//
//            @Override
//            public void propertyChange(PropertyChangeEvent evt) {
//                System.out.printf(evt.getPropertyName());
//                  if (evt.getPropertyName().equals("id")) {
//
//                      idField.setText((String) evt.getNewValue());
//                  }
//            }
//        } );



    }



}
