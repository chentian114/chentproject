package com.chen.design.base.creation.builder.example;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by ChenTian on 2018/4/18.
 */
@Getter
@Setter
@ToString
public class InsuranceContract {
    private String contractId;
    private String personName;
    private String companyName;
    private long beginDate;
    private long endDate;
    private String otherData;

    private InsuranceContract(ConcreteBuilder builder){
        this.contractId = builder.contractId;
        this.personName = builder.personName;
        this.companyName = builder.companyName;
        this.beginDate = builder.beginDate;
        this.endDate = builder.endDate;
        this.otherData = builder.otherData;
    }

    public static class ConcreteBuilder{
        private String contractId;
        private String personName;
        private String companyName;
        private long beginDate;
        private long endDate;
        private String otherData;
        public ConcreteBuilder(String contractId,long beginDate,long endDate){
            this.contractId=contractId;
            this.beginDate=beginDate;
            this.endDate=endDate;
        }
        public ConcreteBuilder buildCompanyName(String companyName){
            this.companyName = companyName;
            return this;
        }
        public ConcreteBuilder buildPersonName(String personName){
            this.personName = personName;
            return this;
        }
        public ConcreteBuilder buildOtherData(String otherData){
            this.otherData=otherData;
            return this;
        }
        public InsuranceContract build(){
            return new InsuranceContract(this);
        }
    }
}
