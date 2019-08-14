package com.dicka.javacart.controller;

import com.dicka.javacart.entities.Product;
import com.dicka.javacart.model.ProductModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/product")
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @GetMapping(value = "/index")
    public String getIndex(ModelMap modelMap){
        ProductModel productModel = new ProductModel();
        modelMap.put("products", productModel.findAll());
        LOGGER.info("getIndex()");
        return "product/index";
    }

    @GetMapping(value = "/add")
    public String getAddProduct(ModelMap modelMap){
        modelMap.put("title", "Form Product");
        return "product/add";
    }

    @PostMapping(value = "/save")
    @ResponseBody
    public String addProduct(@RequestBody Product product){
        ProductModel productModel = new ProductModel();
        productModel.addProduct(product);

        List<Product> products = productModel.findAll();

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        LOGGER.info("DATA LIST PRODUCT : "+gson.toJson(products));
        return "success";
    }
}
