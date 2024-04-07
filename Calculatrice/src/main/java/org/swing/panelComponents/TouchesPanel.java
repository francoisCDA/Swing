package org.swing.panelComponents;

import javax.swing.*;
import java.awt.*;

public class TouchesPanel extends JPanel {

    private GridBagLayout gridBagLayout;

    public TouchesPanel() {

        gridBagLayout = new GridBagLayout();

        setLayout(gridBagLayout);

        for (int i = 0 ; i < 4 ; i++ ) {
           for (int j = 0 ; j < 5 ; j++) {
                JButton touche = new JButton("i : " + i + ", j : " +j)     ;
                GridBagConstraints constraints = new GridBagConstraints();

                constraints.gridx = i ;
                constraints.gridy = j ;

                constraints.weightx = 1 ;
                constraints.weighty = 3 ;

                constraints.fill = GridBagConstraints.BOTH;

                add(touche,constraints);

           }



        }


    }


}
