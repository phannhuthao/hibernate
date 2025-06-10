package org.example.hibernate.session17.bt1andbt2.dao;

import org.example.hibernate.ra.orm.entity.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class AuthDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Customer customer) {
        entityManager.persist(customer);
    }

    public Customer findByUsernameAndPassword(String username, String password) {
        try {
            return entityManager.createQuery(
                            "SELECT c FROM Customer c WHERE c.username = :username AND c.password = :password", Customer.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
