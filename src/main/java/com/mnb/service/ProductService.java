package com.mnb.service;


import com.mnb.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    Product findById(int theId);

    void save(Product theProduct);

    void deleteById(int theId);

    List<Product> findProductByName(String keyword);

    List<Product> findProductByCatalog(int id);
}
