package com.chen.design.base.creation.factoryMethod.example;

/**
 * Created by ChenTian on 2018/4/18.
 */
public class ExportHtmlFactory extends ExportFactory{
    public ExportFile factory(String type){
        if("standard".equals(type)){
            return new ExportStandardHtmlFile();
        }else if ("financial".equals(type)){
            return new ExportFinancialHtmlFile();
        }
        return null;
    }
}
