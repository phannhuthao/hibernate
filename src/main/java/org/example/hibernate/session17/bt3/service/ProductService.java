package org.example.hibernate.session17.bt3.service;

import org.example.hibernate.ra.orm.entity.Product;
import org.example.hibernate.session17.bt3.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;

    public List<Product> getProducts(int page, int size) {
        return productDao.getProducts(page, size);
    }

    public Product findById(int id) {
        return productDao.findById(id);
    }

    public long countProducts() {
        return productDao.countProducts();
    }
}
