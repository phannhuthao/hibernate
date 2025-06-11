package org.example.hibernate.session17and18.bt3.dao;

import org.example.hibernate.ra.orm.entity.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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

    public List<Product> findByFilter(String name, Integer minPrice, Integer maxPrice, int page, int size) {
        String jpql = "SELECT p FROM Product p WHERE 1=1";
        if (name != null && !name.isEmpty()) jpql += " AND lower(p.productName) LIKE :name";
        if (minPrice != null) jpql += " AND p.price >= :min";
        if (maxPrice != null) jpql += " AND p.price <= :max";

        TypedQuery<Product> q = entityManager.createQuery(jpql, Product.class);
        if (name != null && !name.isEmpty()) q.setParameter("name", "%" + name.toLowerCase() + "%");
        if (minPrice != null) q.setParameter("min", minPrice);
        if (maxPrice != null) q.setParameter("max", maxPrice);

        q.setFirstResult((page - 1) * size);
        q.setMaxResults(size);

        return q.getResultList();
    }

    public long countByFilter(String name, Integer minPrice, Integer maxPrice) {
        String jpql = "SELECT COUNT(p) FROM Product p WHERE 1=1";
        if (name != null && !name.isEmpty()) jpql += " AND lower(p.productName) LIKE :name";
        if (minPrice != null) jpql += " AND p.price >= :min";
        if (maxPrice != null) jpql += " AND p.price <= :max";

        TypedQuery<Long> q = entityManager.createQuery(jpql, Long.class);
        if (name != null && !name.isEmpty()) q.setParameter("name", "%" + name.toLowerCase() + "%");
        if (minPrice != null) q.setParameter("min", minPrice);
        if (maxPrice != null) q.setParameter("max", maxPrice);

        return q.getSingleResult();
    }

}
