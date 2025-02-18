package toyProject.demo.admin.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toyProject.demo.admin.presentation.dto.MemberResponseDetail;
import toyProject.demo.auth.AuthenticateMember;
import toyProject.demo.member.application.MemberService;
import toyProject.demo.member.application.dto.MemberInfo;
import toyProject.demo.team.application.TeamService;
import toyProject.demo.team.presentation.dto.TeamResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
    private final MemberService memberService;
    private final TeamService teamService;

    @GetMapping("/users")
    public ResponseEntity<Page<MemberResponseDetail>> readUsers(
            @AuthenticateMember MemberInfo memberInfo,
            @PageableDefault Pageable pageable
    ) {
        if(!memberInfo.admin()){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Page<MemberResponseDetail> users = memberService.findAll(pageable).map(MemberResponseDetail::from);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
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
    public ResponseEntity<Page<TeamResponse.Basic>> readTeams(
            @AuthenticateMember MemberInfo memberInfo,
            @PageableDefault Pageable pageable
    ) {
        if(!memberInfo.admin()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Page<TeamResponse.Basic> team = teamService.findAll(pageable).map(TeamResponse.Basic::of);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }
}
