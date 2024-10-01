package toyProject.demo.player.application;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import toyProject.demo.player.presentation.dto.PlayerRequest;
import toyProject.demo.player.presentation.dto.PlayerResponse;
import toyProject.demo.player.domain.Player;
import toyProject.demo.player.persistence.PlayerRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<PlayerResponse> findAll(){
        List<PlayerResponse> answer = new ArrayList<>();
        List<Player> players = playerRepository.findAll();
        for (Player player : players) {
            answer.add(new PlayerResponse(player));
        }
        return answer;
    }

    public PlayerResponse findById(Long id){
        return new PlayerResponse(
                playerRepository.findById(id).orElseThrow(NoSuchFieldError::new)
        );
    }
    @Transactional
    public void save(PlayerRequest playerRequest){
        Player player = new Player(
                playerRequest.getLevel(),
                playerRequest.getNickname(),
                playerRequest.getFreeTier(),
                playerRequest.getSoloTier()
        );
        playerRepository.save(player);
    }
    @Transactional
    public void update(Long id, PlayerRequest playerRequest){
        Player player = playerRepository.findById(id).orElseThrow(NoSuchFieldError::new);
        player.update(playerRequest);
    }
    @Transactional
    public void delete(Long id){
        playerRepository.deleteById(id);
    }
}
