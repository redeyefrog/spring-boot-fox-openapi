package com.redeyefrog.persistence.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Table(name = "PRODUCT_MASTER", schema = "TEST")
@Entity
public class ProductMasterEntity {

    @GeneratedValue
    @Id
    @Column(name = "MASTER_ID")
    private Long masterId;

    @Column(name = "COMPANY_NAME")
    private String companyName;

    @Column(name = "TOTAL_QUANTITY")
    private BigDecimal totalQuantity;

    @Column(name = "TOTAL_AMOUNT")
    private BigDecimal totalAmount;

    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;

}
