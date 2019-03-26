package com.bayurf.madalinemethod.form;

import com.bayurf.madalinemethod.lib.MadalineLibrary;

import javax.swing.*;
import java.text.DecimalFormat;

public class Madaline {
    public JPanel mainPanel;
    private JTextField w11;
    private JTextField w21;
    private JTextField b_z1;
    private JTextField alphaTF;
    private JTextField toleransiTF;
    private JLabel w11Value;
    private JLabel w21Value;
    private JLabel bias_z1Value;
    private JLabel epochValue;
    private JButton learn;
    private JTextField x1;
    private JTextField x2;
    private JButton testButton;
    private JLabel outputTestValue;
    private JTextField w12;
    private JTextField w22;
    private JTextField b_z2;
    private JLabel bias_z2Value;
    private JLabel w12Value;
    private JLabel w22Value;
    private JLabel statusValue;

    private static double[][] input = {
            { 1, 1},
            { 1,-1},
            {-1, 1},
            {-1,-1}
    };
    private static double[] bobotw1, bobotw2, target = {-1, 1, 1,-1};
    private static double bias1, bias2, alpha, toleransi;
    private DecimalFormat numberFormat = new DecimalFormat("#0.00");

    public Madaline(){
        learn.addActionListener(v -> {
            bobotw1 = new double[]{
                    Double.valueOf(w11.getText()),
                    Double.valueOf(w21.getText())
            };
            bobotw2 = new double[]{
                    Double.valueOf(w12.getText()),
                    Double.valueOf(w22.getText())
            };
            bias1 = Double.valueOf(b_z1.getText());
            bias2 = Double.valueOf(b_z2.getText());
            alpha = Double.valueOf(alphaTF.getText());
            toleransi = Double.valueOf(toleransiTF.getText());

            MadalineLibrary.setAlpha(alpha);
            MadalineLibrary.setToleransi(toleransi);
            MadalineLibrary.setBobotw1(bobotw1);
            MadalineLibrary.setBobotw2(bobotw2);
            MadalineLibrary.setBias(bias1);
            MadalineLibrary.setBias2(bias2);

            MadalineLibrary.learn(input, target);

            bobotw1 = MadalineLibrary.getBobotw1();
            bobotw2 = MadalineLibrary.getBobotw2();

            w11Value.setText(String.valueOf(numberFormat.format(bobotw1[0])));
            w12Value.setText(String.valueOf(numberFormat.format(bobotw1[1])));
            w21Value.setText(String.valueOf(numberFormat.format(bobotw2[0])));
            w22Value.setText(String.valueOf(numberFormat.format(bobotw2[1])));
            bias1 = MadalineLibrary.getBias();
            bias2 = MadalineLibrary.getBias2();
            bias_z1Value.setText(String.valueOf(numberFormat.format(bias1)));
            bias_z2Value.setText(String.valueOf(numberFormat.format(bias2)));
            epochValue.setText(String.valueOf(MadalineLibrary.getIterasi()));
            statusValue.setText(MadalineLibrary.getStatus());
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
