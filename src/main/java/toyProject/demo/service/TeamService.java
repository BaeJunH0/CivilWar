package toyProject.demo.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import toyProject.demo.DTO.team.TeamRequest;
import toyProject.demo.DTO.team.TeamResponse;
import toyProject.demo.domain.Player;
import toyProject.demo.domain.Team;
import toyProject.demo.repository.PlayerRepository;
import toyProject.demo.repository.TeamRepository;

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
