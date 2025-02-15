package toyProject.demo.player.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyProject.demo.player.application.dto.PlayerCommand;
import toyProject.demo.player.application.dto.PlayerInfo;
import toyProject.demo.player.presentation.dto.PlayerRequest;
import toyProject.demo.player.presentation.dto.PlayerResponse;
import toyProject.demo.player.domain.Player;
import toyProject.demo.player.persistence.PlayerRepository;

import java.util.List;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Transactional(readOnly = true)
    public List<PlayerInfo> findAll(){
        return playerRepository.findAll().stream().map(PlayerInfo::from).toList();
    }

    @Transactional(readOnly = true)
    public PlayerInfo findById(Long id){
        return PlayerInfo.from(playerRepository.findById(id).orElseThrow(NoSuchFieldError::new));
    }

    @Transactional
    public void save(PlayerCommand playerCommand){
        playerRepository.save(Player.of(playerCommand.riotId(), playerCommand.riotTag()));
    }

    @Transactional
    public void delete(Long id){
        playerRepository.deleteById(id);
    }
}
