package org.example.hibernate.session17.bt3.dao;

import org.example.hibernate.ra.orm.entity.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ProductDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Product> getProducts(int page, int size) {
        return entityManager.createQuery("SELECT p FROM Product p", Product.class)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }

    public Product findById(int id) {
        return entityManager.find(Product.class, id);
    }

    public long countProducts() {
        return entityManager.createQuery("SELECT COUNT(p) FROM Product p", Long.class).getSingleResult();
    }
}
