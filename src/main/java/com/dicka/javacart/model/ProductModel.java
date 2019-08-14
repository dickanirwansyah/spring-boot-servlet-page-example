package com.dicka.javacart.model;

import com.dicka.javacart.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductModel {

    private List<Product> products;
    public ProductModel(){
        this.products = new ArrayList<>();
        this.products.add(new Product("p001", "iphone", "image1.jpg", 1000000));
        this.products.add(new Product("p002", "samsung", "image2.jpg", 2000000));
        this.products.add(new Product("p003", "xiaomi", "image3", 3000000));
    }

    public List<Product> findAll(){
        return this.products;
    }

    public Product findById(String id){
        for (Product product : this.products){
            if (product.getId().equalsIgnoreCase(id)){
                return product;
            }
        }
        return null;
    }

    public void addProduct(Product product){
        this.products.add(new Product(
                product.getId(),
                product.getName(),
                product.getPhoto(),
                product.getPrice()
        ));
    }
}
