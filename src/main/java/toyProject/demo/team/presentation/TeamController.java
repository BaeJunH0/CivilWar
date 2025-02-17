package toyProject.demo.team.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toyProject.demo.auth.AuthenticateMember;
import toyProject.demo.member.application.dto.MemberInfo;
import toyProject.demo.riot.dto.LeagueEntryDTO;
import toyProject.demo.player.application.dto.PlayerCommand;
import toyProject.demo.player.presentation.dto.PlayerRequest;
import toyProject.demo.team.application.TeamFacade;
import toyProject.demo.team.presentation.dto.TeamRequest;
import toyProject.demo.team.presentation.dto.TeamResponse;
import toyProject.demo.team.application.TeamService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/teams")
public class TeamController {
    private final TeamService teamService;
    private final TeamFacade teamFacade;

    // 내 소유의 팀 조회 ( owner == nickname )
    @GetMapping("/my")
    public ResponseEntity<List<TeamResponse.Basic>> readMyTeams(
            @AuthenticateMember MemberInfo memberInfo
    ) {
        List<TeamResponse.Basic> team = teamService.findMyTeams(memberInfo.nickname()).stream()
                .map(TeamResponse.Basic::of)
                .toList();

        return new ResponseEntity<>(team, HttpStatus.OK);
    }

    // 특정 팀 조회
    @GetMapping("/{id}")
    public ResponseEntity<TeamResponse.Detail> readOneTeam(
            @PathVariable Long id
    ) {
        TeamResponse.Detail team = TeamResponse.Detail.of(teamService.findOneTeam(id));

        return new ResponseEntity<>(team, HttpStatus.OK);
    }

    // 팀 생성
    @PostMapping("")
    public ResponseEntity<Void> createTeam(
            @AuthenticateMember MemberInfo memberInfo,
            @RequestBody TeamRequest teamRequest
    ) {
        teamFacade.saveTeamPlayer(teamRequest, memberInfo.nickname());

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // 팀 수정
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateTeam(
            @AuthenticateMember MemberInfo memberInfo,
            @PathVariable Long id,
            @RequestBody TeamRequest teamRequest
    ){
        teamFacade.updateTeamPlayer(teamRequest, memberInfo.nickname(), id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 팀 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(
            @AuthenticateMember MemberInfo memberInfo,
            @PathVariable Long id
    ){
        teamService.teamDelete(memberInfo.nickname(), id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // 팀 셔플
    @PostMapping("/{id}")
    public ResponseEntity<TeamResponse.Detail> shuffleTeam(
            @PathVariable Long id
    ) {
        // Todo : 알고리즘 구현해서 추가하기 ( 팀 섞기 )
        TeamResponse.Detail team = TeamResponse.Detail.of(teamService.findOneTeam(id));

        return new ResponseEntity<>(team, HttpStatus.OK);
    }
}
