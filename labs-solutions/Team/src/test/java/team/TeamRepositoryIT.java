package team;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TeamRepositoryIT {

    EntityManagerFactory factory;

    TeamRepository teamRepository;

    @BeforeEach
    void init() {
        factory = Persistence.createEntityManagerFactory("pu");
        teamRepository = new TeamRepository(factory);
    }

    @AfterEach
    void end() {
        factory.close();
    }

    @Test
    void testSaveTeam() {
        Team team = new Team("Mancheste", "UK", TeamClass.FIRST,100_000);
        teamRepository.saveTeam(team);
        Long id = team.getId();

        EntityManager em = factory.createEntityManager();
        Team found = em.find(Team.class, id);
        em.close();

        assertThat(found.getCountry()).isEqualTo("UK");
    }

    @Test
    void testFindTeamByTeamName() {
        Team team = new Team("Manchester", "UK", TeamClass.FIRST,100_000);
        team.addPlayer(new Player("Diego",120));
        team.addPlayer(new Player("CR07",1200));
        team.addPlayer(new Player("Pölöskei",20));
        team.addPlayer(new Player("Gerard",520));
        teamRepository.saveTeam(team);

        Team found = teamRepository.findTeamByNameWithPlayers("Manchester");
        assertThat(found.getCountry()).isEqualTo("UK");
        assertThat(found.getPlayers().size()).isEqualTo(4);
        assertThat(found.getPlayers().get(0).getName()).isEqualTo("Diego");
    }

    @Test
    void testUpdatePointsById() {
        Team team = new Team("Mancheste", "UK", TeamClass.FIRST,100_000);
        team.addPlayer(new Player("Diego",120));
        team.addPlayer(new Player("CR07",1200));
        team.addPlayer(new Player("Pölöskei",20));
        team.addPlayer(new Player("Gerard",520));
        teamRepository.saveTeam(team);
        teamRepository.addPointsToTeam(team.getId(), 10);

        EntityManager em = factory.createEntityManager();
        Team found = em.find(Team.class, team.getId());
        em.close();

        assertThat(found.getPoints()).isEqualTo(10);
    }

    @Test
    void testFindTeamsByCountryAndClass() {
        Team team1 = new Team("Manchester", "UK", TeamClass.FIRST,0);
        Team team2 = new Team("Milan", "Italy", TeamClass.FIRST,0);
        Team team3 = new Team("Napoli", "Italy", TeamClass.FIRST,0);
        team1.setPoints(32);
        team2.setPoints(45);
        team3.setPoints(28);

        teamRepository.saveTeam(team1);
        teamRepository.saveTeam(team2);
        teamRepository.saveTeam(team3);

        List<Team> result = teamRepository.findTeamsByCountryAndTeamClass("Italy",TeamClass.FIRST);
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getCountry()).isEqualTo("Italy");
        assertThat(result.stream().map(Team::getName).toList()).isEqualTo(List.of("Milan","Napoli"));
    }
}
