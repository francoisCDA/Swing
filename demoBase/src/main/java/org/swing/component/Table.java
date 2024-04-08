package org.swing.component;

import lombok.Data;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.sql.SQLOutput;

@Data
public class Table {

    private JTable jtb;

    public Table() {

        String[][] rows = {{"100","Pierre","65000"},
                {"101","Piero","1200"},
                {"102","Pierrick","6560"},
                {"103","PierrY","15100"},
                {"104","Pier","36002"}};

        String[] colum = {"ID","PSEUDO","POINTS"};

        jtb = new JTable(rows,colum);

        jtb.setBounds(30,40,500,500);

        JScrollPane scrollPane = new JScrollPane(jtb);

        jtb.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                int row = jtb.getSelectedRow();

                if (row < 0) {
                    System.out.println("Le tableau est vide");
                } else {

                    int modelRow = jtb.convertRowIndexToModel(row);

                    System.out.println(String.format("Info selectect in: %d. Dans model : %d. Autre info %s, %s, %s",row,modelRow,jtb.getModel().getValueAt(modelRow,0),jtb.getModel().getValueAt(modelRow,1),jtb.getModel().getValueAt(modelRow,2)));
                }
            }
        });

    }

}
