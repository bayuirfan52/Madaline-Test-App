package com.bayurf.madalinemethod;

import com.bayurf.madalinemethod.form.Madaline;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Madaline");
        frame.setContentPane(new Madaline().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setAlwaysOnTop(true);
        frame.setResizable(true);
        frame.pack();
        frame.setVisible(true);
    }
}
