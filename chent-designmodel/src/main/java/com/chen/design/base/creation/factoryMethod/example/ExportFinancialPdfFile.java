package com.chen.design.base.creation.factoryMethod.example;

/**
 * Created by ChenTian on 2018/4/18.
 */
public class ExportFinancialPdfFile implements ExportFile {
    public void export() {
        System.out.println("export FinancialPdfFile");
    }
}
