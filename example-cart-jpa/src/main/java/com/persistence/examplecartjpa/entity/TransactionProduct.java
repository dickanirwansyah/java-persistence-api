package com.persistence.examplecartjpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TABLE_TRANSACTION_PRODUCT")
public class TransactionProduct implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "TRANSACTION_ID")
    @JsonIgnore
    private Transaction transaction;

    @Column(name = "PRICE")
    private Long price;

    @Column(name = "QTY")
    private int qty;
}
