package toyProject.demo.player.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toyProject.demo.player.presentation.dto.PlayerRequest;
import toyProject.demo.player.presentation.dto.PlayerResponse;
import toyProject.demo.player.application.PlayerService;

import java.util.List;

@RestController
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/api/players")
    public ResponseEntity<List<PlayerResponse>> readPlayers(){
        List<PlayerResponse> players = playerService.findAll();
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @GetMapping("/api/players/{id}")
    public ResponseEntity<PlayerResponse> readPlayer(@PathVariable("id") Long id){
        PlayerResponse player = playerService.findById(id);
        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    @PostMapping("/api/players")
    public ResponseEntity<Void> createPlayer(@RequestBody PlayerRequest playerRequest){
        playerService.save(playerRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/api/players/{id}")
    public ResponseEntity<Void> updatePlayer(@PathVariable Long id, @RequestBody PlayerRequest playerRequest){
        playerService.update(id, playerRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/api/players/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id){
        playerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
