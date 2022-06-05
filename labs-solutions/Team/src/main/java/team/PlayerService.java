package team;

import java.util.List;

public class PlayerService {

    private PlayerRepository repository;

    public PlayerService(PlayerRepository repository) {
        this.repository = repository;
    }

    public Player save(Player player) {
        return repository.savePlayerWithoutTeam(player);
    }

    public List<Player> findAll() {
        return repository.findAll();
    }
}
