package com.pucpr.imc.model;

public class DataModel {
    private static DataModel instance = new DataModel();
    private DataModel(){
    }

    public static DataModel getInstance(){
        return instance;
    }
    public UserIMC userIMC =
            new UserIMC("caianehardt@gmail.com");
}
