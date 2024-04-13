package org.example.view.component;

import com.sun.source.tree.TryTree;
import com.toedter.calendar.JDateChooser;
import org.example.model.Employee;
import org.example.model.Qualification;
import org.example.view.component.listener.DateListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.sql.Date;

public class FormulairePanel extends JPanel {

    private Employee employee;

    public Employee getEmployee() {return employee;}

    public FormulairePanel(Employee employee, Border border) {
        this.employee = employee;
        setBorder(border);
        setPreferredSize(new Dimension(810, 280));

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

                        employee.addPropertyChangeListener("id", new PropertyChangeListener() {
                            @Override
                            public void propertyChange(PropertyChangeEvent evt) {
                                if (employee.getId() != null) {
                                    idField.setText(employee.getId().toString());
                                } else {
                                    idField.setText("");
                                }
                            }
                        });

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

                    buttonGroup.add(radioMale);
                    buttonGroup.add(radioFemale);
                    genderPanel.add(genderLabel);
                    genderPanel.add(radioMale);
                    genderPanel.add(radioFemale);

                    radioMale.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            employee.setIsFemaleNoEvent(false);
                        }
                    });

                    radioFemale.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            employee.setIsFemaleNoEvent(true);
                        }
                    });

                    employee.addPropertyChangeListener("isFemale", new PropertyChangeListener() {
                        @Override
                        public void propertyChange(PropertyChangeEvent evt) {
                            if (employee.isFemale()) {
                                radioFemale.setSelected(true);
                            } else {
                                radioMale.setSelected(true);
                            }
                        }
                    });


            genderPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
            westPanel.add(genderPanel);

            JPanel agePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                JLabel ageLabel = new JLabel("Age");
                ageLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
                agePanel.add(ageLabel);
                JTextField ageField = new JTextField(10);
                    //ageField.setInputVerifier(intVerifier);
                    ageField.getDocument().addDocumentListener(new DocumentListener() {

                        @Override
                        public void insertUpdate(DocumentEvent e) {
                            updateEmployeeAge();
                        }

                        @Override
                        public void removeUpdate(DocumentEvent e) {
                            updateEmployeeAge();
                        }

                        @Override
                        public void changedUpdate(DocumentEvent e) {
                            updateEmployeeAge();
                        }
                        private void updateEmployeeAge() {
                            employee.setAgeNoEvent(ageField.getText());                        }
                    });
                    employee.addPropertyChangeListener("age", new PropertyChangeListener() {
                        @Override
                        public void propertyChange(PropertyChangeEvent evt) {
                            if (employee.getAge() != null) {
                                ageField.setText(employee.getAge().toString());
                            } else {
                           //     SwingUtilities.invokeLater(() -> {
                                ageField.setText("");
                           //     });
                            }
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

                bloodField.getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        updateBloodType();
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        updateBloodType();
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        updateBloodType();
                    }

                    private void updateBloodType() {
                        employee.setBloodtypeNoEvent(bloodField.getText());
                    }
                });

                employee.addPropertyChangeListener("bloodType", new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        bloodField.setText(employee.getBloodtype());
                    }
                });


                bloodPanel.add(bloodField);
            bloodPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
            westPanel.add(bloodPanel);

            JPanel numberPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                JLabel numberLabel = new JLabel("Phone Number");
                numberLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
                numberPanel.add(numberLabel);
                JTextField numberField = new JTextField(10);
                numberField.getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        updateEmployeeNumber();
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        updateEmployeeNumber();
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        updateEmployeeNumber();
                    }
                    private void updateEmployeeNumber() {
                        employee.setPhoneNoEvent(numberField.getText());
                    }
                });

                employee.addPropertyChangeListener("phoneNumber", new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        numberField.setText(employee.getPhoneNumber());
                    }
                });
                numberPanel.add(numberField);
            numberPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
            westPanel.add(numberPanel);

            // Qualifications
            JPanel qualifPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                JLabel qualifLabel = new JLabel("Qualification");
                qualifLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
                qualifPanel.add(qualifLabel);
                    String[] qualifs = new String[Qualification.values().length];
                    for (int i = 0; i < Qualification.values().length; i++) {
                        qualifs[i] = Qualification.values()[i].toString();
                    }
                JComboBox<String> comboBox = new JComboBox<>(qualifs);

                    comboBox.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            employee.setQualificationNoEvent(Qualification.values()[comboBox.getSelectedIndex()]);

                        }
                    });


                employee.addPropertyChangeListener("qualification", new PropertyChangeListener() {

                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        if (employee.getQualification() != null) {
                            comboBox.setSelectedItem(employee.getQualification().toString());
                        }

                    }
                });


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

                 dateChooser.setDateFormatString("yyyy-MM-dd");

                 dateChooser.addPropertyChangeListener(new DateListener(employee));

                 employee.addPropertyChangeListener("startDate", new PropertyChangeListener() {
                     @Override
                     public void propertyChange(PropertyChangeEvent evt) {
                         if (employee.getStartDate() != null) {
                             dateChooser.setDate(Date.valueOf(employee.getStartDate()));
                         } else {
                             dateChooser.setDate(null);
                         }

                     }
                 });


                 datePanel.add(dateChooser);
            datePanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
            westPanel.add(datePanel);

        add(westPanel, BorderLayout.WEST);

        // EAST

        JPanel estPanel = new JPanel(null);
        estPanel.setLayout(new FlowLayout());
        estPanel.setSize(150,300);
        JPanel photoPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        photoPanel.setPreferredSize(new Dimension(150, 200));
        photoPanel.setBackground(Color.blue);
        // photoPanel.setBorder(border);
        JLabel photoLabel = new JLabel();
        photoPanel.add(photoLabel);
        estPanel.add(photoPanel);

        add(estPanel, BorderLayout.EAST);

        // CENTER
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

            //address
            JPanel addressPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                 JLabel addressLabel = new JLabel("Address");
                 addressLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
                 addressPanel.add(addressLabel);
                 JTextArea addressTextArea = new JTextArea(10, 26);
                // addressTextArea.setPreferredSize(new Dimension(200, 300));
                 addressTextArea.getDocument().addDocumentListener(new DocumentListener() {

                     @Override
                     public void insertUpdate(DocumentEvent e) {
                        updateAddress();
                     }

                     @Override
                     public void removeUpdate(DocumentEvent e) {
                         updateAddress();
                     }

                     @Override
                     public void changedUpdate(DocumentEvent e) {
                         updateAddress();
                     }
                     private void updateAddress() {
                         String address = addressTextArea.getText();
                         employee.setAddressNoEvent(address);
                     }
                 });
                 employee.addPropertyChangeListener("address", new PropertyChangeListener() {

                     @Override
                     public void propertyChange(PropertyChangeEvent evt) {
                         addressTextArea.setText(employee.getAddress());
                     }
                 });

                 JScrollPane scrollPane = new JScrollPane(addressTextArea);
                 addressPanel.add(scrollPane);
          //  addressPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
            centerPanel.add(addressPanel);


            // Bouton de chargement d'image
           JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                JButton photoButton = new JButton("Upload photo");
                photoButton.setPreferredSize(new Dimension(340, 22));
                buttonPanel.add(photoButton);
               // buttonPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);

           centerPanel.add(buttonPanel);

            JPanel pathPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                  JLabel pathLabel = new JLabel("Path image");
                  pathLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
                  pathPanel.add(pathLabel);
                  JTextField pathField = new JTextField(29);
                  //pathField.setPreferredSize(new Dimension(320, 20));
                  pathField.setFont(new Font("Tahoma", Font.PLAIN, 12));
                  pathField.setEditable(false);

                employee.addPropertyChangeListener("photoPath", new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        pathField.setText(employee.getPhotoPath());

                        if (employee.getPhotoPath() != null) {

                            File photoEmployee = new File(employee.getPhotoPath());

                            ImageIcon imageIcon = new ImageIcon(photoEmployee.getAbsolutePath());

                            Image scalePhoto = imageIcon.getImage().getScaledInstance(160, -1, Image.SCALE_SMOOTH);
                            ImageIcon photoIcon = new ImageIcon(scalePhoto);

                            photoLabel.setIcon(photoIcon);
                        } else {
                            photoLabel.setIcon(null);
                        }


                    }
                });

                  pathPanel.add(pathField);
                 /// pathPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
            centerPanel.add(pathPanel);

    add(centerPanel, BorderLayout.CENTER);



        photoButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {

                File selectedFile = fileChooser.getSelectedFile();

                if (selectedFile.exists() && selectedFile.isFile()) {

                    String path = selectedFile.getAbsolutePath();

                    employee.setPhotoPath(path);

                }



            }
        });





    }



}
