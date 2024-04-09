package org.cda.view;

import org.cda.view.dialog.InsertDialog;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    private JPanel jPanel;

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    MainFrame mainFrame = new MainFrame();
                   // mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    mainFrame.setSize(370,500);
                    mainFrame.setTitle("Main Frame");
                    mainFrame.setLocationRelativeTo(null);
                    mainFrame.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        });
    }

    private MainFrame(){

        jPanel = new JPanel();
        jPanel.setBorder(new EmptyBorder(5,5,5,5));

        setContentPane(jPanel);
        jPanel.setLayout(null);

        JButton btnInsert = new JButton("Insert");
        btnInsert.setBounds(30,40 ,100,35);

        btnInsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InsertDialog dialog = new InsertDialog();
              //  dialog.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
                dialog.setLocationRelativeTo(jPanel);
                dialog.setVisible(true);
            }
        });


        JButton btnUpdate = new JButton("Update");
        btnUpdate.setBounds(130,40 ,100,35);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setBounds(230,40 ,100,35);

        JButton btnDisplay = new JButton("Display");
        btnDisplay.setBounds(130,90 ,100,35);

        jPanel.add(btnInsert);
        jPanel.add(btnUpdate);
        jPanel.add(btnDisplay);
        jPanel.add(btnDelete);



    }

}
