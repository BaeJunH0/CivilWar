package toyProject.demo.player.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toyProject.demo.player.application.PlayerService;
import toyProject.demo.player.application.dto.PlayerCommand;
import toyProject.demo.player.application.dto.PlayerInfo;
import toyProject.demo.player.presentation.dto.PlayerRequest;
import toyProject.demo.player.presentation.dto.PlayerResponse;
import toyProject.demo.riot.dto.LeagueEntryDTO;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/players")
public class PlayerController {
    private final PlayerService playerService;

    // 플레이어 검색
    @PostMapping()
    public ResponseEntity<PlayerResponse> playerSearch(
            @RequestBody PlayerRequest.Search playerRequest
    ) {
        PlayerInfo playerInfo = playerService.searchPlayer(PlayerCommand.to(playerRequest));

        return new ResponseEntity<>(PlayerResponse.of(playerInfo), HttpStatus.OK);
    }
}
