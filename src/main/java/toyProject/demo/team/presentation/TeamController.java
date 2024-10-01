package toyProject.demo.team.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toyProject.demo.team.presentation.dto.TeamRequest;
import toyProject.demo.team.presentation.dto.TeamResponse;
import toyProject.demo.team.application.TeamService;

import java.util.List;

@RestController
public class TeamController {
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/api/teams")
    public ResponseEntity<List<TeamResponse>> readTeams(){
        List<TeamResponse> teams = teamService.findAll();
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @GetMapping("/api/teams/{id}")
    public ResponseEntity<TeamResponse> readTeam(@PathVariable Long id){
        TeamResponse team = teamService.findOne(id);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }

    @PostMapping("/api/teams")
    public ResponseEntity<Void> createTeam(@RequestBody TeamRequest teamRequest){
        teamService.save(teamRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/api/teams/{id}")
    public ResponseEntity<Void> updateTeam(@PathVariable Long id, @RequestBody TeamRequest teamRequest){
        teamService.update(id, teamRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/api/teams/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id){
        teamService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
