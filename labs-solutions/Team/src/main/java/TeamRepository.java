import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class TeamRepository {

    EntityManagerFactory factory;

    public TeamRepository(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public Team saveTeam(Team team) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(team);
        em.getTransaction().commit();
        em.close();
        return team;
    }

    public Team findTeamByTeamName(String name) {
        EntityManager em = factory.createEntityManager();
        Team team = em.createQuery("select t from Team t join fetch t.players where t.name = :name", Team.class)
                .setParameter("name", name)
                .getSingleResult();
        em.close();
        return team;
    }

    public void updatePointsById(long id, int points) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Team team = em.getReference(Team.class, id);
        team.setPoints(points);
        em.getTransaction().commit();
        em.close();
    }

    public List<Team> findTeamsByCountryAndClass(String country, Class classType) {
        EntityManager em = factory.createEntityManager();
        List<Team> teams = em.createQuery("select t from Team t where t.country= :country and t.classType= :classtype order by t.points desc", Team.class)
                .setParameter("country", country)
                .setParameter("classtype", classType)
                .getResultList();
        em.close();
        return teams;
    }
}
