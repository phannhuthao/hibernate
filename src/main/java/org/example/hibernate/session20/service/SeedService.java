package org.example.hibernate.session20.service;

import org.example.hibernate.ra.orm.entity.Movie;
import org.example.hibernate.ra.orm.entity.Seed;
import org.example.hibernate.session20.dao.SeedDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SeedService {

    @Autowired
    private SeedDao seedDao;

    @Transactional
    public List<Seed> getAllSeed() {
        return seedDao.findAll();
    }

    @Transactional
    public void addSeed(Seed seed) {
        seedDao.save(seed);
    }

    @Transactional
    public void editSeed(Seed seed) {
        seedDao.edit(seed);
    }

    @Transactional
    public void deleteSeed(Seed seed) {
        seedDao.delete(seed);
    }

    public boolean isSeedNameExists(String name) {
        return seedDao.findAll().stream()
                .anyMatch(s -> s.getSeedName().equalsIgnoreCase(name));
    }

    public List<Seed> searchSeed(String name, Integer minPrice, Integer maxPrice) {
        return seedDao.search(name, minPrice, maxPrice);
    }
}

