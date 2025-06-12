package org.example.hibernate.session19.bt2.dao;


import org.example.hibernate.ra.orm.entity.Movie;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MovieDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Movie> findAll() {
        return entityManager.createQuery("FROM Movie", Movie.class).getResultList();
    }

    public Movie findById(Long id) {
        return entityManager.find(Movie.class, id);
    }

    public void save(Movie movie) {
        entityManager.merge(movie);
    }

    public void delete(Movie movie) {
        entityManager.remove(entityManager.contains(movie) ? movie : entityManager.merge(movie));
    }
}

