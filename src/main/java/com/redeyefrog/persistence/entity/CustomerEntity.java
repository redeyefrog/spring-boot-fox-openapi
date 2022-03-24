package com.redeyefrog.persistence.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Table(name = "CUSTOMER", schema = "TEST")
@Entity
public class CustomerEntity {

    @Id
    @Column(name = "NAME")
    private String name;

    @Column(name = "AGE")
    private Integer age;

    @Column(name = "BIRTHDAY")
    private LocalDate birthday;

    @Column(name = "BLOOD")
    private String blood;

}
