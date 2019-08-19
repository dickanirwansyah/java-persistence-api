package com.persistence.examplecartjpa.model;

import com.persistence.examplecartjpa.entity.Product;


public class TransactionProductModel {

    private Product product;
    private long price;
    private int qty;

    public TransactionProductModel(){}

    public TransactionProductModel(Product product, long price, int qty){
        this.product = product;
        this.price = price;
        this.qty = qty;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "TransactionProductModel{" +
                "product=" + product +
                ", price=" + price +
                ", qty=" + qty +
                '}';
    }
}
