package main.java.movie;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Optional;

public class MovieRepository {

    private EntityManagerFactory factory;

    public MovieRepository(EntityManagerFactory factory) {
        this.factory = factory;
    }
    public Movie saveMovie(Movie movie) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(movie);
        em.getTransaction().commit();
        em.close();
    }
    public Optional<Movie> findByTitle(String title) {
        EntityManager em = factory.createEntityManager();
        Movie movie = em.createQuery("select m from Movie m where m.title = :title", Movie.class)
                .setParameter("title",title)
                .getSingleResult();
        em.close();
        return Optional.of(movie);
    }

}
