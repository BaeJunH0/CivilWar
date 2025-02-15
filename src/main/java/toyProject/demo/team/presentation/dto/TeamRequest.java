package toyProject.demo.team.presentation.dto;

import java.util.Set;

public record TeamRequest(String name, Set<Long> playerIds) {
}
