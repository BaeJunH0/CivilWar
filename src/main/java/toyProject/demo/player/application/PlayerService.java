package toyProject.demo.player.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    public List<PlayerResponse> findAll(){
        return playerRepository.findAll().stream().map(PlayerResponse::from).toList();
    }

    @Transactional(readOnly = true)
    public PlayerResponse findById(Long id){
        return PlayerResponse.from(playerRepository.findById(id).orElseThrow(NoSuchFieldError::new));
    }

    @Transactional
    public void save(PlayerRequest playerRequest){
        playerRepository.save(PlayerRequest.toEntityFrom(playerRequest));
    }

    @Transactional
    public void update(Long id, PlayerRequest playerRequest){
        playerRepository.findById(id).orElseThrow(NoSuchFieldError::new)
                .update(playerRequest);
    }

    @Transactional
    public void delete(Long id){
        playerRepository.deleteById(id);
    }
}
