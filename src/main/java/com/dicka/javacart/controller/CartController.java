package com.dicka.javacart.controller;

import com.dicka.javacart.entities.Item;
import com.dicka.javacart.model.ProductModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/cart")
public class CartController {

    private static final Logger log = LoggerFactory.getLogger(CartController.class);

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String getIndex(){
        return "cart/index";
    }

    @RequestMapping(value = "/buy/{id}", method = RequestMethod.GET)
    public String buy(@PathVariable("id") String id, HttpSession httpSession){
        ProductModel productModel = new ProductModel();
        GsonBuilder gsonBuilder;
        List<Item> cart = null;
        if (httpSession.getAttribute("cart") == null){
            cart = new ArrayList<>();
            cart.add(new Item(productModel.findById(id), 1));
            httpSession.setAttribute("cart", cart);
        }else{
            cart = (List<Item>) httpSession.getAttribute("cart");
            int index = this.productIsExistsInCart(id, cart);
            if (index == -1){
                cart.add(new Item(productModel.findById(id), 1));
            }else{
                int quantity = cart.get(index).getQuantity() + 1;
                cart.get(index).setQuantity(quantity);
            }
            httpSession.setAttribute("cart", cart);
            //test gson to json
        }
        gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        System.out.println("CART JSON --> "+gson.toJson(cart));
        return "redirect:/cart/index";
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public String remove(@PathVariable("id") String id, HttpSession httpSession){
        ProductModel productModel = new ProductModel();
        List<Item> cart = (List<Item>) httpSession.getAttribute("cart");
        int index = this.productIsExistsInCart(id, cart);
        cart.remove(index);
        httpSession.setAttribute("cart", cart);
        return "redirect:/cart/index";
    }

    private int productIsExistsInCart(String id, List<Item> cart){
        for (int i=0; i < cart.size(); i++){
            if (cart.get(i).getProduct().getId().equalsIgnoreCase(id)){
                return i;
            }
        }
        return -1;
    }
}
