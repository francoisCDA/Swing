package org.swing.layout;

import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

@Data
public class TaquinLayout {

    private int size;

    private JPanel panel;

    private JPanel messagePanel;

    private List<JButton> pave;

    private int freeX;
    private int freeY;

    public TaquinLayout(int size) {

        this.size = size;

        freeX = size -1 ;
        freeY = size -1 ;

        panel = new JPanel();

        panel.setLayout(new GridBagLayout());

        pave = new ArrayList<>();

        int count = 0;

        for (int i = 0 ; i < size ; i ++) {
            for (int j = 0 ; j < size ; j++) {

                if (i == freeX && j == freeY) {
                    break;
                }

                int label = ++count;

                addButton(j,i,Integer.toString(label));
            }


        }

    }


    private void addButton(int x, int y, String label){

        JButton newButton = new JButton(label);
        newButton.addActionListener(e -> {
            if ( abs(x-freeY) == 1 ^ abs(y-freeX) == 1   ) {
                addButton(freeY,freeX,label);
                freeY = x;
                freeX = y;
                panel.remove(newButton);
            }
        });

        GridBagConstraints contraintes = new GridBagConstraints();

        contraintes.fill = GridBagConstraints.BOTH;

        contraintes.gridx = x ;
        contraintes.gridy = y ;

        panel.add(newButton,contraintes);

        panel.revalidate();
        panel.repaint();
    }


}
