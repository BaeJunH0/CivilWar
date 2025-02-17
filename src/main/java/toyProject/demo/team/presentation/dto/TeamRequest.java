package toyProject.demo.team.presentation.dto;

import toyProject.demo.player.presentation.dto.PlayerRequest;

import java.util.List;


public record TeamRequest(List<PlayerRequest> playerRequests, String name) {
}
