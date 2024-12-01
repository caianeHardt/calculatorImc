package com.pucpr.imc.model;

public class HistoryIMC {
    private String higth;
    private String size;
    private String age;
    private String imc;

    public void historyIMC(String higth, String size, String age, String imc) {
        this.higth = higth;
        this.size = size;
        this.age = age;
        this.imc = imc;
    }

    public String[] getIMC(String height, String size, String age, String imc) {
        return new String[]{age, size, height, imc};
    }
}