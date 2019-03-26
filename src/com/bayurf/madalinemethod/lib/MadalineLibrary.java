package com.bayurf.madalinemethod.lib;

import java.util.Arrays;

public class MadalineLibrary {
    private static double[] bobotw1, bobotw2, bobotz = { 0.5, 0.5 };
    private static double deltaW1 = 0.1, deltaW2 = 0.1;
    private static double bias, bias2, bias3 = 0.5, alpha, toleransi;
    private static int iterasi = 0, epoch = 1;

    public static void learn(double[][] input, double[] target){
        double praOutput1, praOutput2,zIn_1, zIn_2, hasil, output;

        do {
            System.out.println(" ");
            if (iterasi == input.length){
                iterasi = 0;
                epoch++;
            }
            zIn_1 = iterasiCekHitung(input[iterasi], bobotw1, bias);
            zIn_2 = iterasiCekHitung(input[iterasi], bobotw2, bias2);

            praOutput1 = funActivation(zIn_1);
            praOutput2 = funActivation(zIn_2);

            output = iterasiCekHitung(new double[]{praOutput1, praOutput2}, bobotz, bias3);
            hasil = funActivation(output);
            System.out.println("Value " + hasil + ", target : "+ target[iterasi]);
            if (hasil != target[iterasi]) {
                if (hasil == 1) {
                    System.out.println("Value != target.  1");
                    if (getCloseToZero(zIn_1, zIn_2) == zIn_1) {
                        System.out.println("Update Z_In 1");
                        updateBobotZinClosetoZero(input[iterasi], zIn_1,1);
                    } else {
                        System.out.println("Update Z_In 2");
                        updateBobotZinClosetoZero(input[iterasi], zIn_2,2);
                    }
                } else {
                    System.out.println("Value != target. -1");
                    if (zIn_1 > 0){
                        System.out.println("Update Z_In 1");
                        updateBobotZinPositive(input[iterasi], zIn_1, 1);
                    }
                    else {
                        System.out.println("Update Z_In 2");
                        updateBobotZinPositive(input[iterasi], zIn_2, 2);
                    }
                }
            }

            if (deltaW1 < toleransi || deltaW2 < toleransi){
                System.out.println("Melewati batas toleransi, deltaW1 : " + deltaW1 + ", deltaW2 : " + deltaW2);
                break;
            }
            System.out.println("iterasi : " + iterasi + ", epoch : " + epoch);
            iterasi++;

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while (epoch != 1000);
        System.out.println("Selesai");
    }

    public static double test(double[] input){
        double hasil, zIn1, zIn2, z1, z2, output;
        zIn1 = iterasiCekHitung(input, bobotw1, bias);
        zIn2 = iterasiCekHitung(input, bobotw2, bias2);

        z1 = funActivation(zIn1);
        z2 = funActivation(zIn2);

        output = iterasiCekHitung(new double[]{z1,z2}, bobotz, bias3);
        hasil = funActivation(output);

        return hasil;
    }

    private static void updateBobotZinClosetoZero(double[] input, double z_in, int status){
        double[] deltaW = new double[input.length];
        for (int i = 0; i < input.length; i++){
            if (status == 1) {
                bobotw1[i] = bobotw1[i] + alpha * (1 - z_in);
                deltaW[i] = bobotw1[i];
            }
            else {
                bobotw2[i] = bobotw2[i] + alpha * (1 - z_in);
                deltaW[i] = bobotw2[i];
            }
            bias2 = bias2 + alpha * (-1 - z_in) * input[i];
            System.out.println("bobot : "+ bobotw2[i]);
        }
        System.out.println("bias : "+ bias2);
        if (status == 1) deltaW1 = Math.abs(Arrays.stream(deltaW).max().getAsDouble());
        else deltaW2 = Math.abs(Arrays.stream(deltaW).max().getAsDouble());
    }

    private static void updateBobotZinPositive(double[] input, double z_in, int status){
        double[] deltaW = new double[input.length];
        for (int i = 0; i < input.length; i++){
            if (status == 1) {
                bobotw1[i] = bobotw1[i] + alpha * (-1 - z_in);
                deltaW[i] = bobotw1[i];
            }
            else {
                bobotw2[i] = bobotw2[i] + alpha * (-1 - z_in);
                deltaW[i] = bobotw2[i];
            }
            bias2 = bias2 + alpha * (-1 - z_in) * input[i];
            System.out.println("bobot : "+ bobotw2[i]);
        }
        System.out.println("bias : "+ bias2);
        if (status == 1) deltaW1 = Math.abs(Arrays.stream(deltaW).max().getAsDouble());
        else deltaW2 = Math.abs(Arrays.stream(deltaW).max().getAsDouble());
    }

    private static double iterasiCekHitung(double[] input, double[] bobot, double bias){
        double hasil, sum = 0;
        for (int i = 0; i < input.length; i++){
            sum = sum + bobot[i] + input[i];
        }

        hasil = bias + sum;

        return hasil;
    }

    private static int funActivation(double input){
        int output;
        if (input >= 0) output = 1;
        else output = -1;

        return output;
    }

    private static double getCloseToZero(double z_in1, double z_in2){
        double output;
        double[] sort = {z_in1, z_in2};
        Arrays.sort(sort);
        output = sort[0];
        return output;
    }

    public static double getBias() {
        return bias;
    }

    public static void setBias(double bias) {
        MadalineLibrary.bias = bias;
    }

    public static void setAlpha(double aplha) {
        MadalineLibrary.alpha = aplha;
    }

    public static void setToleransi(double toleransi) {
        MadalineLibrary.toleransi = toleransi;
    }

    public static int getIterasi() {
        return epoch;
    }

    public static double[] getBobotw1() {
        return bobotw1;
    }

    public static void setBobotw1(double[] bobotw1) {
        MadalineLibrary.bobotw1 = bobotw1;
    }

    public static double[] getBobotw2() {
        return bobotw2;
    }

    public static void setBobotw2(double[] bobotw2) {
        MadalineLibrary.bobotw2 = bobotw2;
    }

    public static double getBias2() {
        return bias2;
    }

    public static void setBias2(double bias2) {
        MadalineLibrary.bias2 = bias2;
    }
}
