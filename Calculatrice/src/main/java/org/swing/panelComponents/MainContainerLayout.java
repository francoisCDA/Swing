package org.swing.panelComponents;

import javax.swing.*;
import java.awt.*;

public class MainContainerLayout extends JPanel {

    private GridBagLayout gridBagLayout;

    private JLabel affichage;

    private TouchesPanel touchesPanel;

    private String resultat;


    private Double nombre1;


    private String operateur;


    public MainContainerLayout() {

        resultat = "";

        operateur = "";


        gridBagLayout = new GridBagLayout();

        setLayout(gridBagLayout);

        affichage = new JLabel(resultat);


        GridBagConstraints affichageConstraint = new GridBagConstraints();

        affichageConstraint.gridx = 3;
        affichageConstraint.gridy = 0;

        affichageConstraint.weightx = 1 ;
        affichageConstraint.weighty = 1;
       // affichageConstraint.fill = GridBagConstraints.BOTH;
        add(affichage,affichageConstraint);


        JButton buttonC = new JButton("C");
        buttonC.addActionListener(e -> toucheC() );
        GridBagConstraints constraintsC = new GridBagConstraints();
        constraintsC.gridx = 0;
        constraintsC.gridy = 1;
        constraintsC.weightx = 1;
        constraintsC.weighty = 1;
        constraintsC.fill = GridBagConstraints.BOTH;
        add(buttonC,constraintsC);

        JButton buttonPM = new JButton("+/-");
        buttonPM.addActionListener(e -> swithSign() );
        GridBagConstraints constraintsPM = new GridBagConstraints();
        constraintsPM.gridx = 1;
        constraintsPM.gridy = 1;
        constraintsPM.weightx = 1;
        constraintsPM.weighty = 1;
        constraintsPM.fill = GridBagConstraints.BOTH;
        add(buttonPM,constraintsPM);

        JButton buttonPC = new JButton("%");
        GridBagConstraints constraintsPC = new GridBagConstraints();
        constraintsPC.gridx = 2;
        constraintsPC.gridy = 1;
        constraintsPC.weightx = 1;
        constraintsPC.weighty = 1;
        constraintsPC.fill = GridBagConstraints.BOTH;
        add(buttonPC,constraintsPC);

        JButton buttonDiv = new JButton("/");
        buttonDiv.addActionListener(e -> handleOperator("/"));
        GridBagConstraints constraintsDiv = new GridBagConstraints();
        constraintsDiv.gridx = 3;
        constraintsDiv.gridy = 1;
        constraintsDiv.weightx = 1;
        constraintsDiv.weighty = 1;
        constraintsDiv.fill = GridBagConstraints.BOTH;
        add(buttonDiv,constraintsDiv);

//////

        JButton button7 = new JButton("7");
        button7.addActionListener(e -> handleNumber(7));
        GridBagConstraints constraints7 = new GridBagConstraints();
        constraints7.gridx = 0;
        constraints7.gridy = 2;
        constraints7.weightx = 1;
        constraints7.weighty = 1;
        constraints7.fill = GridBagConstraints.BOTH;
        add(button7,constraints7);

        JButton button8 = new JButton("8");
        button8.addActionListener(e -> handleNumber(8));
        GridBagConstraints constraints8 = new GridBagConstraints();
        constraints8.gridx = 1;
        constraints8.gridy = 2;
        constraints8.weightx = 1;
        constraints8.weighty = 1;
        constraints8.fill = GridBagConstraints.BOTH;
        add(button8,constraints8);

        JButton button9 = new JButton("9");
        button9.addActionListener(e -> handleNumber(9));
        GridBagConstraints constraints9 = new GridBagConstraints();
        constraints9.gridx = 2;
        constraints9.gridy = 2;
        constraints9.weightx = 1;
        constraints9.weighty = 1;
        constraints9.fill = GridBagConstraints.BOTH;
        add(button9,constraints9);

        JButton buttonMult = new JButton("X");
        buttonMult.addActionListener(e -> handleOperator("x"));
        GridBagConstraints constraintsMult = new GridBagConstraints();
        constraintsMult.gridx = 3;
        constraintsMult.gridy = 2;
        constraintsMult.weightx = 1;
        constraintsMult.weighty = 1;
        constraintsMult.fill = GridBagConstraints.BOTH;
        add(buttonMult,constraintsMult);

        //////

        JButton button4 = new JButton("4");
        button4.addActionListener(e -> handleNumber(4));
        GridBagConstraints constraints4 = new GridBagConstraints();
        constraints4.gridx = 0;
        constraints4.gridy = 3;
        constraints4.weightx = 1;
        constraints4.weighty = 1;
        constraints4.fill = GridBagConstraints.BOTH;
        add(button4,constraints4);

        JButton button5 = new JButton("5");
        button5.addActionListener(e -> handleNumber(5));
        GridBagConstraints constraints5 = new GridBagConstraints();
        constraints5.gridx = 1;
        constraints5.gridy = 3;
        constraints5.weightx = 1;
        constraints5.weighty = 1;
        constraints5.fill = GridBagConstraints.BOTH;
        add(button5,constraints5);

        JButton button6 = new JButton("6");
        button6.addActionListener(e -> handleNumber(6));
        GridBagConstraints constraints6 = new GridBagConstraints();
        constraints6.gridx = 2;
        constraints6.gridy = 3;
        constraints6.weightx = 1;
        constraints6.weighty = 1;
        constraints6.fill = GridBagConstraints.BOTH;
        add(button6,constraints6);

        JButton buttonMoins = new JButton("-");
        buttonMoins.addActionListener(e -> handleOperator("-"));
        GridBagConstraints constraintsMoins = new GridBagConstraints();
        constraintsMoins.gridx = 3;
        constraintsMoins.gridy = 3;
        constraintsMoins.weightx = 1;
        constraintsMoins.weighty = 1;
        constraintsMoins.fill = GridBagConstraints.BOTH;
        add(buttonMoins,constraintsMoins);

        //////

        JButton button1 = new JButton("1");
        button1.addActionListener((e -> handleNumber(1)));
        GridBagConstraints constraints1 = new GridBagConstraints();
        constraints1.gridx = 0;
        constraints1.gridy = 4;
        constraints1.weightx = 1;
        constraints1.weighty = 1;
        constraints1.fill = GridBagConstraints.BOTH;
        add(button1,constraints1);

        JButton button2 = new JButton("2");
        button2.addActionListener(e -> handleNumber(2));
        GridBagConstraints constraints2 = new GridBagConstraints();
        constraints2.gridx = 1;
        constraints2.gridy = 4;
        constraints2.weightx = 1;
        constraints2.weighty = 1;
        constraints2.fill = GridBagConstraints.BOTH;
        add(button2,constraints2);

        JButton button3 = new JButton("3");
        button3.addActionListener(e -> handleNumber(3));
        GridBagConstraints constraints3 = new GridBagConstraints();
        constraints3.gridx = 2;
        constraints3.gridy = 4;
        constraints3.weightx = 1;
        constraints3.weighty = 1;
        constraints3.fill = GridBagConstraints.BOTH;
        add(button3,constraints3);

        JButton buttonPlus = new JButton("+");
        buttonPlus.addActionListener(e -> handleOperator("+"));
        GridBagConstraints constraintsPlus = new GridBagConstraints();
        constraintsPlus.gridx = 3;
        constraintsPlus.gridy = 4;
        constraintsPlus.weightx = 1;
        constraintsPlus.weighty = 1;
        constraintsPlus.fill = GridBagConstraints.BOTH;
        add(buttonPlus,constraintsPlus);

        //////

        JButton button0 = new JButton("0");
        button0.addActionListener(e -> handleNumber(0));
        GridBagConstraints constraints0 = new GridBagConstraints();
        constraints0.gridx = 0;
        constraints0.gridy = 5;
        constraints0.gridwidth = 2;   //aucun effet ??
        constraints0.weighty = 1;
        constraints0.fill = GridBagConstraints.BOTH;
        add(button0,constraints0);

        JButton buttonComma = new JButton(",");
        buttonComma.addActionListener(e -> handleOperator(","));
        GridBagConstraints constraintsComma = new GridBagConstraints();
        constraintsComma.gridx = 2;
        constraintsComma.gridy = 5;
        constraintsComma.weightx = 1;
        constraintsComma.weighty = 1;
        constraintsComma.fill = GridBagConstraints.BOTH;
        add(buttonComma,constraintsComma);

        JButton buttonEgal = new JButton("=");
        buttonEgal.addActionListener(e -> handleOperator("="));
        GridBagConstraints constraintsEgal = new GridBagConstraints();
        constraintsEgal.gridx = 3;
        constraintsEgal.gridy = 5;
        constraintsEgal.weightx = 1;
        constraintsEgal.weighty = 1;
        constraintsEgal.fill = GridBagConstraints.BOTH;
        add(buttonEgal,constraintsEgal);


    }

    private void swithSign() {

        double nb = Double.parseDouble(resultat) * -1;

        resultat = Double.toString(nb);

        updAffichage();

    }

    private void handleOperator(String ope) {

        if (ope.equals(",")){
            resultat+=".";
            updAffichage();
            return;
        }

        if (operateur.isEmpty() && !resultat.isEmpty()) {
            operateur = ope;
            nombre1 = Double.parseDouble(resultat);
        } else {

            Double nombre2 = Double.parseDouble(resultat);


            switch (operateur) {
                case "+" -> nombre1 += nombre2;
                case "-" -> nombre1 -= nombre2;
                case "x" -> nombre1 *= nombre2;
                case "/" -> nombre1 /= nombre2;
            }

            operateur = ope;

            if (operateur.equals("=")) operateur = "";

            resultat = nombre1.toString();

            updAffichage();

        }

    }


    private void handleNumber(Integer i) {
            if (!operateur.isEmpty() && !resultat.endsWith(".")) {
                resultat += i.toString();
            }else {
                resultat += i.toString();
            }

            updAffichage();

    }

    private void toucheC() {

        resultat = "";
        operateur = "";
        nombre1 = null;
        updAffichage();

    }


    private void updAffichage() {
        if (resultat.equals("")){
            affichage.setText("0");
        } else {
            if (resultat.endsWith(".0")) {
                resultat = resultat.substring(0,resultat.length()-2);
            }
            affichage.setText(resultat);
        }
    }


}
