package com.persistence.examplecartjpa.controller.page;

import com.persistence.examplecartjpa.model.TransactionAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    private static final Logger log = LoggerFactory.getLogger(IndexController.class);

//    @Autowired
//    private TransactionAction transactionAction;

    @GetMapping(value = {"/", "/home", "/index"})
    public String getIndex(Model model){
        model.addAttribute("title", "Home");
        return "index";
    }

    @GetMapping(value = "/product")
    public String getProducts(Model model){
        model.addAttribute("title", "Data Product");
        return "content/product";
    }

    @GetMapping(value = "/transaction")
    public String getTransaction(Model model){
        model.addAttribute("title", "Transaction");
        return "content/transaction";
    }

//    @GetMapping(value = "/add-to-cart/{productId}")
//    @ResponseBody
//    public void addToCart(@PathVariable(value = "productId") int productId, HttpSession httpSession){
//        transactionAction.addTransaction(productId, httpSession);
//    }

    @GetMapping(value = "/remove-to-cart/{productId}")
    public String removeToCart(@PathVariable(value = "productId")int productId, HttpSession httpSession){
        return "redirect:/content/cart-item";
    }
}
