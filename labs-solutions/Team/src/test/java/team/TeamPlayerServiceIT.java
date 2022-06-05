package team;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TeamPlayerServiceIT {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");

    TeamRepository teamRepository = new TeamRepository(factory);

    PlayerRepository playerRepository = new PlayerRepository(factory);

    TeamPlayerService service = new TeamPlayerService(teamRepository, playerRepository);

    Team chelsea;

    Player john;

    Team arsenal;

    @BeforeEach
    void initData() {

        chelsea = teamRepository.saveTeam(new Team("Chelsea", "England", TeamClass.FIRST, 10_000_000));
        john = playerRepository.savePlayerWithoutTeam(new Player("John", 100_000));
        service.transferPlayer(chelsea.getId(), john.getId());
        arsenal = teamRepository.saveTeam(new Team("Arsenal", "England", TeamClass.FIRST, 10_000_000));
    }

    @Test
    void testTransfer() {
        service.transferPlayer(arsenal.getId(), john.getId());
        john = playerRepository.findById(john.getId());
        assertEquals("Arsenal", john.getTeam().getName());
    }

    @Test
    void testBudgetTransfer() {
        service.transferPlayer(arsenal.getId(), john.getId());
        chelsea = teamRepository.findTeamById(chelsea.getId());
        assertEquals(10_100_000, chelsea.getBudget());

        arsenal = teamRepository.findTeamById(arsenal.getId());
        assertEquals(9_900_000, arsenal.getBudget());
    }
}