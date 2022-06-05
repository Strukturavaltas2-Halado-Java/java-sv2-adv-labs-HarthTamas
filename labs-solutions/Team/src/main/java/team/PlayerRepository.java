package team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class PlayerRepository {

    private EntityManagerFactory factory;

    public PlayerRepository(EntityManagerFactory factory) {
        this.factory = factory;
    }


    public Player savePlayerWithoutTeam(Player player){
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(player);
        em.getTransaction().commit();
        em.close();
        return player;
    }

    public Player savePlayerWithTeam(Player player, long teamId){
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Team team = em.getReference(Team.class,teamId);
        player.setTeam(team);
        em.persist(player);
        em.getTransaction().commit();
        em.close();
        return player;
    }

    public Player findById(long playerId){
        EntityManager em = factory.createEntityManager();
        Player player = em.find(Player.class, playerId);
        em.close();
        return player;
    }

    public Player updatePlayerTeam(long playerId, long teamId){
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Team team = em.getReference(Team.class,teamId);
        Player player = em.getReference(Player.class, playerId);
        player.setTeam(team);
        em.getTransaction().commit();
        em.close();
        return player;
    }

    public List<Player> findAll() {
        EntityManager em = factory.createEntityManager();
        List<Player> players =  em.createQuery("select p from Player p", Player.class).getResultList();
        em.close();
        return players;
    }

    public void deleteAll() {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("delete Player p").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
}
