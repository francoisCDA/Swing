package org.example.view.component;

import com.sun.source.tree.TryTree;
import com.toedter.calendar.JDateChooser;
import org.example.model.Employee;
import org.example.model.Qualification;
import org.example.util.PhotoUtil;
import org.example.view.component.listener.DateListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.sql.Date;

public class FormulairePanel extends JPanel {

    private Employee activeEmployee;


    public FormulairePanel(Employee employe, Border border) {
        this.activeEmployee = employe;

        Border mainPadding = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        Border mainBorder = BorderFactory.createCompoundBorder(mainPadding, border);

        setBorder(mainBorder);

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

                         activeEmployee.addPropertyChangeListener("id", new PropertyChangeListener() {
                            @Override
                            public void propertyChange(PropertyChangeEvent evt) {
                                if (activeEmployee.getId() != null) {
                                    idField.setText(activeEmployee.getId().toString());
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
                              activeEmployee.setNameNoEvent(name);
                          }
                      });
                      activeEmployee.addPropertyChangeListener("name", new PropertyChangeListener() {
                          @Override
                          public void propertyChange(PropertyChangeEvent evt) {
                                nameField.setText(activeEmployee.getName());
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
                            activeEmployee.setIsFemaleNoEvent(false);
                        }
                    });

                    radioFemale.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            activeEmployee.setIsFemaleNoEvent(true);
                        }
                    });

                    activeEmployee.addPropertyChangeListener("isFemale", new PropertyChangeListener() {
                        @Override
                        public void propertyChange(PropertyChangeEvent evt) {
                            if (activeEmployee.isFemale()) {
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
                            activeEmployee.setAgeNoEvent(ageField.getText());                        }
                    });
                    activeEmployee.addPropertyChangeListener("age", new PropertyChangeListener() {
                        @Override
                        public void propertyChange(PropertyChangeEvent evt) {
                            if (activeEmployee.getAge() != null) {
                                ageField.setText(activeEmployee.getAge().toString());
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
                        activeEmployee.setBloodtypeNoEvent(bloodField.getText());
                    }
                });

                activeEmployee.addPropertyChangeListener("bloodType", new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        bloodField.setText(activeEmployee.getBloodtype());
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
                        activeEmployee.setPhoneNoEvent(numberField.getText());
                    }
                });

                activeEmployee.addPropertyChangeListener("phoneNumber", new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        numberField.setText(activeEmployee.getPhoneNumber());
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
                            activeEmployee.setQualificationNoEvent(Qualification.values()[comboBox.getSelectedIndex()]);
                        }
                    });

               activeEmployee.addPropertyChangeListener("qualification", new PropertyChangeListener() {

                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        if (activeEmployee.getQualification() != null) {
                            comboBox.setSelectedItem(activeEmployee.getQualification().toString());
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

                 dateChooser.addPropertyChangeListener(new DateListener(activeEmployee));

                 activeEmployee.addPropertyChangeListener("startDate", new PropertyChangeListener() {
                     @Override
                     public void propertyChange(PropertyChangeEvent evt) {
                         if (activeEmployee.getStartDate() != null) {
                             dateChooser.setDate(Date.valueOf(activeEmployee.getStartDate()));
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

        JLabel photoLabel = new JLabel();
        photoLabel.setHorizontalAlignment(JLabel.CENTER);
        photoLabel.setVerticalAlignment(JLabel.CENTER);
        photoLabel.setIcon(PhotoUtil.getDefault());

        Border padding = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        Border photoBorder = BorderFactory.createCompoundBorder(padding, border);

        photoLabel.setBorder(photoBorder);

        add(photoLabel, BorderLayout.EAST);

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
                         activeEmployee.setAddressNoEvent(address);
                     }
                 });
                activeEmployee.addPropertyChangeListener("address", new PropertyChangeListener() {

                     @Override
                     public void propertyChange(PropertyChangeEvent evt) {
                         addressTextArea.setText(activeEmployee.getAddress());
                     }
                 });

                 JScrollPane scrollPane = new JScrollPane(addressTextArea);
                 addressPanel.add(scrollPane);
          //  addressPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
            centerPanel.add(addressPanel);


            // Bouton de chargement d'image
           JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                JButton photoButton = new JButton("Upload photo",PhotoUtil.getIcone("src/main/resources/icone/telecharger.png",18));
                photoButton.setPreferredSize(new Dimension(340, 22));
                buttonPanel.add(photoButton);

           centerPanel.add(buttonPanel);

            JPanel pathPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                  JLabel pathLabel = new JLabel("Path image");
                  pathLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
                  pathPanel.add(pathLabel);
                  JTextField pathField = new JTextField(29);
                  pathField.setFont(new Font("Tahoma", Font.PLAIN, 12));
                  pathField.setEditable(false);

                 activeEmployee.addPropertyChangeListener("photoPath", new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        pathField.setText(activeEmployee.getPhotoPath());

                        if (activeEmployee.getPhotoPath() != null) {

                            File photoEmployee = new File(activeEmployee.getPhotoPath());

                            ImageIcon imageIcon = new ImageIcon(photoEmployee.getAbsolutePath());

                            Image scalePhoto = imageIcon.getImage().getScaledInstance(160, -1, Image.SCALE_SMOOTH);
                            ImageIcon photoIcon = new ImageIcon(scalePhoto);

                            photoLabel.setIcon(photoIcon);
                        } else {
                            photoLabel.setIcon(PhotoUtil.getDefault());
                        }
                    }
                });

                  pathPanel.add(pathField);
            centerPanel.add(pathPanel);

    add(centerPanel, BorderLayout.CENTER);

        photoButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {

                File selectedFile = fileChooser.getSelectedFile();

                if (selectedFile.exists() && selectedFile.isFile()) {

                    String path = selectedFile.getAbsolutePath();

                    activeEmployee.setPhotoPath(path);
                }
            }
        });
    }

}
