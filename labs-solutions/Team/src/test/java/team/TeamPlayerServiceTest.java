package team;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TeamPlayerServiceTest {

    @Mock
    TeamRepository teamRepository;

    @Mock
    PlayerRepository playerRepository;

    @InjectMocks
    TeamPlayerService service;

    @Test
    void testNotEnoughBudget() {
        when(teamRepository.findTeamById(2L)).thenReturn(new Team("Arsenal", "x", TeamClass.FIRST, 100));

        Player john = new Player("John Doe", 100_000);
        john.setTeam(new Team("Chelsea", "x", TeamClass.FIRST, 900));
        when(playerRepository.findById(1L)).thenReturn(john);

        assertThrows(NotEnoughBudgetException.class,
                () -> service.transferPlayer(2L, 1L));
    }

    @Test
    void testTransferPlayerWithoutTeam() {
        when(teamRepository.findTeamById(2L)).thenReturn(null);
        Player john = new Player("John Doe", 100_000);
        Team chelsea = teamRepository.saveTeam(new Team("Chelsea", "England", TeamClass.FIRST, 10_000_000));

        when(playerRepository.findById(1L)).thenReturn(john);

        service.transferPlayer(2, 1);

        verify(playerRepository,times(1)).updatePlayerTeam(anyLong(), anyLong());
        verify(teamRepository,never()).updateBudget(anyLong(),anyInt());
    }
}
