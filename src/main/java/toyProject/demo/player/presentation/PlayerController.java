package toyProject.demo.player.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toyProject.demo.player.application.dto.PlayerCommand;
import toyProject.demo.player.presentation.dto.PlayerRequest;
import toyProject.demo.player.presentation.dto.PlayerResponse;
import toyProject.demo.player.application.PlayerService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/players")
public class PlayerController {
    private final PlayerService playerService;

    // 관리자용
    @GetMapping
    public ResponseEntity<List<PlayerResponse>> readPlayers(){
        List<PlayerResponse> players = playerService.findAll().stream().map(PlayerResponse::from).collect(Collectors.toList());
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerResponse> readPlayer(@PathVariable("id") Long id){
        PlayerResponse player = PlayerResponse.from(playerService.findById(id));
        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createPlayer(@RequestBody PlayerRequest playerRequest){
        playerService.save(PlayerCommand.from(playerRequest));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id){
        playerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
