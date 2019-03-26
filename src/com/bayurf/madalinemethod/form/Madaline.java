package com.bayurf.madalinemethod.form;

import com.bayurf.madalinemethod.lib.MadalineLibrary;

import javax.swing.*;

public class Madaline {
    public JPanel mainPanel;
    private JTextField w1;
    private JTextField w2;
    private JTextField b;
    private JTextField alphaTF;
    private JTextField toleransiTF;
    private JLabel w1Value;
    private JLabel w2Value;
    private JLabel biasValue;
    private JLabel epochValue;
    private JButton learn;
    private JTextField x1;
    private JTextField x2;
    private JButton testButton;
    private JLabel outputTestValue;

    private static double[][] input = {
            { 1, 1},
            { 1,-1},
            {-1, 1},
            {-1,-1}
    };
    private static double[] bobot, target = {-1, 1, 1,-1};
    private static double bias, alpha, toleransi;

    public Madaline(){
        learn.addActionListener(v -> {
            bobot = new double[]{
                    Double.valueOf(w1.getText()),
                    Double.valueOf(w2.getText())
            };
            bias = Double.valueOf(b.getText());
            alpha = Double.valueOf(alphaTF.getText());
            toleransi = Double.valueOf(toleransiTF.getText());

            MadalineLibrary.setAplha(alpha);
            MadalineLibrary.setToleransi(toleransi);
            MadalineLibrary.setBobot(bobot);
            MadalineLibrary.setBias(bias);
            MadalineLibrary.learn(input,target);
            bobot = MadalineLibrary.getBobot();
            w1Value.setText(String.valueOf(bobot[0]));
            w2Value.setText(String.valueOf(bobot[1]));
            bias = MadalineLibrary.getBias();
            biasValue.setText(String.valueOf(bias));
            epochValue.setText(String.valueOf(MadalineLibrary.getIterasi()));
        });

        testButton.addActionListener(v -> {
            double[] x = {
                    Double.valueOf(x1.getText()),
                    Double.valueOf(x2.getText())
            };
            outputTestValue.setText(String.valueOf(MadalineLibrary.test(x)));
        });
    }
}
