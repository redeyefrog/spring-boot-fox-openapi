package com.redeyefrog.persistence.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Table(name = "PRODUCT_DETAIL", schema = "TEST")
@Entity
public class ProductDetailEntity {

    @GeneratedValue
    @Id
    @Column(name = "DETAIL_ID")
    private Long detailId;

    @Column(name = "MASTER_ID")
    private Long masterId;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "QUANTITY")
    private BigDecimal quantity;

    @Column(name = "UNIT_PRICE")
    private BigDecimal unitPrice;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;

    @Column(name = "UPDATE_DATE")
    private LocalDateTime updateDate;

    @ManyToOne
    @JoinColumn(name = "MASTER_ID", insertable = false, updatable = false)
    private ProductMasterEntity productMaster;

}
