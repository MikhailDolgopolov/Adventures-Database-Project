package com.mikhaildolgopolov.spring.models;

import jakarta.persistence.*;

@Entity
@Table(name="tran_mcc_codes")
public class TransactionData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer mcc_code;
    private String code_description;

    public Integer getMcc_code() {
        return mcc_code;
    }

    public void setMcc_code(Integer mcc_code) {
        this.mcc_code = mcc_code;
    }

    public String getCode_description() {
        return code_description;
    }

    public void setCode_description(String code_description) {
        this.code_description = code_description;
    }
}
