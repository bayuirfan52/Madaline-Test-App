package com.bayurf.madalinemethod.lib;

public class MadalineLibrary {
    private static double[] bobot;
    private static double bias, aplha, toleransi;
    private static int iterasi;

    public static void learn(double[][] input, double[] target){

    }

    public static double test(double[] input){
        double hasil = 0;

        return hasil;
    }

    public static double[] getBobot() {
        return bobot;
    }

    public static void setBobot(double[] bobot) {
        MadalineLibrary.bobot = bobot;
    }

    public static double getBias() {
        return bias;
    }

    public static void setBias(double bias) {
        MadalineLibrary.bias = bias;
    }

    public static double getAplha() {
        return aplha;
    }

    public static void setAplha(double aplha) {
        MadalineLibrary.aplha = aplha;
    }

    public static double getToleransi() {
        return toleransi;
    }

    public static void setToleransi(double toleransi) {
        MadalineLibrary.toleransi = toleransi;
    }

    public static int getIterasi() {
        return iterasi;
    }
}
