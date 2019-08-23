package com.persistence.examplecartjpa.controller.page;

import com.persistence.examplecartjpa.entity.Product;
import com.persistence.examplecartjpa.entity.TransactionProduct;
import com.persistence.examplecartjpa.model.TransactionModel;
import com.persistence.examplecartjpa.model.TransactionProductModel;
import com.persistence.examplecartjpa.service.ProductService;
import com.persistence.examplecartjpa.service.TransactionalService;
import com.persistence.examplecartjpa.util.CurrencyRupiah;
import com.persistence.examplecartjpa.util.UtilSession;
import org.omg.IOP.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    private static final Logger log = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private TransactionalService transactionalService;

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
        model.addAttribute("products", productService.listProduct());
        return "content/transaction";
    }

    @GetMapping(value = "/add-to-cart")
    public String addToCart(@RequestParam(value = "productId") int productId,
                            HttpSession httpSession){

        List<TransactionProductModel> productModels = null;
        TransactionModel transactionModel = new TransactionModel();
        Product product = productService.getIdProduct(productId);
        if (httpSession.getAttribute("cart") == null){
            productModels = new ArrayList<>();
            productModels.add(new TransactionProductModel(product, product.getPrice(), 1));

            transactionModel.setTransactionProductModels(productModels);
            httpSession.setAttribute("cart", productModels);

        }else{
            productModels = (List<TransactionProductModel>) httpSession.getAttribute("cart");
            int index = productExisting(productId, productModels);
            if (index == -1){
                productModels.add(new TransactionProductModel(product, product.getPrice(), 1));
            }else{
                int qty = productModels.get(index).getQty() + 1;
                productModels.get(index).setQty(qty);
            }
            httpSession.setAttribute("cart", productModels);
        }

        return "redirect:/cart-item-product";
    }


    private int productExisting(int productId, List<TransactionProductModel> productModels){
        for (int i=0; i < productModels.size(); i++){
            if (String.valueOf(productModels.get(i).getProduct().getId())
                    .equalsIgnoreCase(String.valueOf(productId))){
                return i;
            }
        }
        return -1;
    }

    @GetMapping(value = "/cart-item-product")
    public String getCartItem(Model model, HttpServletRequest servletRequest){

        model.addAttribute("transactionModel", new TransactionModel());
        model.addAttribute("title", "Asuransi Pilihan Anda");
        List<TransactionProductModel> productModels = (List<TransactionProductModel>) servletRequest
                .getSession().getAttribute("cart");

        double total = 0;
        if (servletRequest.getSession().getAttribute("cart") == null){
            model.addAttribute("nullSession", "Pilih Produk Asuransi Pilihan Anda");
        }else{
            for (TransactionProductModel productModel : productModels){
                total += productModel.getProduct().getPrice() * productModel.getQty();
            }
        }

        DecimalFormat decimalFormat = CurrencyRupiah.rpCurrency(total);

        model.addAttribute("amountTotal", String.valueOf(decimalFormat));
        model.addAttribute("cartSession", productModels);
        return "content/cart-item";
    }


    @PostMapping(value = "/save-transaction")
    public String saveTransaction(TransactionModel transactionModel,
                                  HttpServletRequest request){

        transactionalService.insertTransaction(transactionModel, request);
        request.getSession().removeAttribute("cart");
        return "redirect:/product";
    }

    @GetMapping(value = "/remove-to-cart/{productId}")
    public String removeToCart(@PathVariable(value = "productId")int productId, HttpSession httpSession){
        return "redirect:/content/cart-item";
    }
}
