package toyProject.demo.player.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import toyProject.demo.player.application.PlayerService;
import toyProject.demo.player.application.dto.PlayerCommand;
import toyProject.demo.player.presentation.dto.PlayerRequest;
import toyProject.demo.riot.dto.LeagueEntryDTO;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/players")
public class PlayerController {
    private final PlayerService playerService;

    // 플레이어 검색
    @GetMapping()
    public ResponseEntity<List<LeagueEntryDTO>> playerSearch(
            @RequestBody PlayerRequest playerRequest
    ) {
        List<LeagueEntryDTO> leagueEntryDTOS = playerService.searchPlayer(PlayerCommand.to(playerRequest));

        return new ResponseEntity<>(leagueEntryDTOS, HttpStatus.OK);
    }
}
