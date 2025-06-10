package org.example.hibernate.session17.bt4.dao;

import org.example.hibernate.ra.orm.entity.ProductCart;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CartDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(ProductCart productCart) {
        entityManager.persist(productCart);
    }

    public void update(ProductCart productCart) {
        entityManager.merge(productCart);
    }

    public ProductCart findByCustomerIdAndProductId(int customerId, int productId) {
        String jpql = "SELECT c FROM ProductCart c WHERE c.customerId = :customerId AND c.productId = :productId";
        TypedQuery<ProductCart> query = entityManager.createQuery(jpql, ProductCart.class);
        query.setParameter("customerId", customerId);
        query.setParameter("productId", productId);
        List<ProductCart> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    public List<ProductCart> findByCustomerId(int customerId) {
        String jpql = "SELECT c FROM ProductCart c WHERE c.customerId = :customerId";
        return entityManager.createQuery(jpql, ProductCart.class)
                .setParameter("customerId", customerId)
                .getResultList();
    }
}
