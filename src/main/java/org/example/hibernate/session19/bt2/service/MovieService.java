package org.example.hibernate.session19.bt2.service;

import org.example.hibernate.ra.orm.entity.Movie;
import org.example.hibernate.session19.bt2.dao.MovieDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieDao movieDao;

    @Transactional
    public List<Movie> getAllMovies() {
        return movieDao.findAll();
    }

    @Transactional
    public void addMovie(Movie movie) {
        movieDao.save(movie);
    }

    @Transactional
    public void updateMovie(Movie movie) {
        movieDao.save(movie);
    }

    @Transactional
    public void deleteMovie(Long id) {
        Movie movie = movieDao.findById(id);
        if (movie != null) {
            movieDao.delete(movie);
        }
    }

    @Transactional
    public Movie getMovieById(Long id) {
        return movieDao.findById(id);
    }
}
