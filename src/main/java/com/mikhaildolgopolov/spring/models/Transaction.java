package com.mikhaildolgopolov.spring.models;

import jakarta.persistence.*;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer tran_uid;
    private Integer mcc_code;
    private Integer customer_id;
    private LocalDateTime tr_datetime;
    private Integer tr_type;
    private BigDecimal amount;
    private Integer term_id;

    public Integer getTran_uid(){ return tran_uid;}

    public void setTran_uid(Integer uid) {tran_uid=uid;}

    public Integer getMcc_code(){ return mcc_code;}
    public void setMcc_code(Integer code) {mcc_code=code;}

    public Integer getCustomer_id(){return customer_id;}
    public void setCustomer_id(Integer id){customer_id=id;}

    public LocalDateTime getTr_datetime() {return tr_datetime;}
    public void setTr_datetime(LocalDateTime time){tr_datetime=time;}

    public Integer getTr_type(){return tr_type;}
    public void setTr_type(Integer type){tr_type=type;}

    public BigDecimal getAmount(){return amount;}
    public void setAmount(BigDecimal money){amount=money;}

    public Integer getTerm_id(){return term_id;}
    public void setTerm_id(Integer term) {term_id=term;}

}
