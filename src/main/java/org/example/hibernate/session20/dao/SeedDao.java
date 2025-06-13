package org.example.hibernate.session20.dao;

import org.example.hibernate.ra.orm.entity.Seed;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class SeedDao {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Seed> findAll() {
        return entityManager.createQuery("FROM Seed", Seed.class).getResultList();
    }

    public void save(Seed seed) {
        entityManager.merge(seed);
    }

    public void edit(Seed seed) {
        entityManager.merge(seed);
    }

    public void delete(Seed seed) {
        entityManager.remove(entityManager.contains(seed) ? seed : entityManager.merge(seed));
    }

    public Seed searchByNameAndByPrice(int id,  String name, int price) {
        return entityManager.find(Seed.class, id);
    }

    public List<Seed> search(String name, Integer minPrice, Integer maxPrice) {
        StringBuilder query = new StringBuilder("FROM Seed s WHERE 1=1");
        if (name != null && !name.isEmpty()) {
            query.append(" AND LOWER(s.seedName) LIKE LOWER(:name)");
        }
        if (minPrice != null) {
            query.append(" AND s.price >= :minPrice");
        }
        if (maxPrice != null) {
            query.append(" AND s.price <= :maxPrice");
        }

        TypedQuery<Seed> q = entityManager.createQuery(query.toString(), Seed.class);
        if (name != null && !name.isEmpty()) {
            q.setParameter("name", "%" + name + "%");
        }
        if (minPrice != null) {
            q.setParameter("minPrice", minPrice);
        }
        if (maxPrice != null) {
            q.setParameter("maxPrice", maxPrice);
        }

        return q.getResultList();
    }

}
