package toyProject.demo.admin.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toyProject.demo.auth.AuthenticateMember;
import toyProject.demo.member.application.MemberService;
import toyProject.demo.member.application.dto.MemberInfo;
import toyProject.demo.member.presentation.dto.MemberRequest;
import toyProject.demo.member.presentation.dto.MemberResponse;
import toyProject.demo.team.application.TeamService;
import toyProject.demo.team.presentation.dto.TeamResponse;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
    private final MemberService memberService;
    private final TeamService teamService;

    @GetMapping("users")
    public ResponseEntity<List<MemberResponse>> readUsers(@AuthenticateMember MemberInfo memberInfo) {
        if(!memberInfo.admin()){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        List<MemberResponse> users =
        memberService.findAll().stream().map(MemberResponse::from).collect(Collectors.toList());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<Void> deleteUser(
            @AuthenticateMember MemberInfo memberInfo,
            @PathVariable Long id
    ) {
        if(!memberInfo.admin()){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        memberService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/teams")
    public ResponseEntity<List<TeamResponse.Basic>> readTeams(
            @AuthenticateMember MemberInfo memberInfo
    ) {
        if(!memberInfo.admin()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        List<TeamResponse.Basic> team = teamService.findAll().stream().map(TeamResponse.Basic::of).toList();
        return new ResponseEntity<>(team, HttpStatus.OK);
    }
}
