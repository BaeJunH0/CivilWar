package toyProject.demo.team.application;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import toyProject.demo.team.presentation.dto.TeamRequest;
import toyProject.demo.team.presentation.dto.TeamResponse;
import toyProject.demo.player.domain.Player;
import toyProject.demo.team.domain.Team;
import toyProject.demo.player.persistence.PlayerRepository;
import toyProject.demo.team.persistence.TeamRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class TeamService {
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    public TeamService(TeamRepository teamRepository, PlayerRepository playerRepository) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
    }

    public List<TeamResponse> findAll(){
        List<TeamResponse> answer = new ArrayList<>();
        List<Team> teams = teamRepository.findAll();
        for (Team team : teams) {
            answer.add(new TeamResponse(team));
        }
        return answer;
    }

    public TeamResponse findOne(Long id){
        Team team = teamRepository.findById(id).orElseThrow(NoSuchFieldError::new);
        return new TeamResponse(team);
    }
    @Transactional
    public void save(TeamRequest teamRequest){
        teamRepository.save(new Team(teamRequest.getName()));
    }
    @Transactional
    public void update(Long id, TeamRequest teamRequest){
        Team team = teamRepository.findById(id).orElseThrow(NoSuchFieldError::new);
        team.changeName(teamRequest.getName());

        Set<Long> playerIds = teamRequest.getPlayerIds();
        for (Long playerId : playerIds) {
            Player player = playerRepository.findById(playerId).orElseThrow(NoSuchFieldError::new);
            team.addPlayer(player);
        }
    }
    @Transactional
    public void delete(Long id){
        teamRepository.deleteById(id);
    }
}
