package movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
        return movie;
    }

    public Optional<Movie> findByTitle(String title) {
        EntityManager em = factory.createEntityManager();
        Movie movie = em.createQuery("select m from Movie m where m.title = :title", Movie.class)
                .setParameter("title",title)
                .getSingleResult();
        em.close();
        return Optional.of(movie);
    }

    public Optional<Movie> findByTitleWithRatings(String title) {
        EntityManager em = factory.createEntityManager();
        Movie movie = em.createQuery("select m from Movie m left join fetch m.ratings where m.title = :title", Movie.class)
                .setParameter("title",title)
                .getSingleResult();
        em.close();
        return Optional.of(movie);
    }

}
