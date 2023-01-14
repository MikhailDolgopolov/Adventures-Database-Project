package com.mikhaildolgopolov.spring.models;

import jakarta.persistence.*;

@Entity
@Table(name = "tran_types")
public class TransactionType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer tr_type;
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getTr_type() {
        return tr_type;
    }

    public void setTr_type(Integer tr_type) {
        this.tr_type = tr_type;
    }
}
