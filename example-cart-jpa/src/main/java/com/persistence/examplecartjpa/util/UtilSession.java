package com.persistence.examplecartjpa.util;

import com.persistence.examplecartjpa.model.TransactionModel;

import javax.servlet.http.HttpServletRequest;

public class UtilSession {

    public static TransactionModel getCartSession(HttpServletRequest servletRequest){
        TransactionModel transactionModel = (TransactionModel) servletRequest.getSession().getAttribute("cart");
        //check
        if (transactionModel == null){
            transactionModel = new TransactionModel();
            servletRequest.getSession().setAttribute("cart", transactionModel);
        }
        return transactionModel;
    }

    public static void getStoreData(HttpServletRequest request, TransactionModel transactionModel){
        request.getSession().setAttribute("cart", transactionModel);
    }
}
