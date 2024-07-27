package com.policeschool.code.dao;

import com.policeschool.code.domain.Product;

import java.util.List;

public interface ProductMapper {
    Product find(Long id);

    List<Product> findByName(String name);

    int save(Product product);
}
