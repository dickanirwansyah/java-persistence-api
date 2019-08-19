package com.persistence.examplecartjpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TABLE_TRANSACTION")
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "CURRENT_DATE")
    private Date currentDate;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @OneToMany(mappedBy = "transaction", fetch = FetchType.LAZY)
    private Set<TransactionProduct> transactionProducts = new HashSet<>();

}
