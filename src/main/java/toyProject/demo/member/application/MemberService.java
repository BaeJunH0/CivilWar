package toyProject.demo.member.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyProject.demo.exception.CustomException;
import toyProject.demo.exception.errorCode.AuthErrorCode;
import toyProject.demo.member.application.dto.MemberCommand;
import toyProject.demo.member.application.dto.MemberInfo;
import toyProject.demo.member.domain.Member;
import toyProject.demo.member.persistence.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public void register(MemberCommand memberCommand){
        if(memberRepository.existsByEmail(memberCommand.email())){
            throw CustomException.of(AuthErrorCode.ALREADY_EXIST_EMAIL);
        }

        memberRepository.save(MemberCommand.toEntity(memberCommand));
    }

    @Transactional(readOnly = true)
    public void login(MemberCommand memberCommand) {
        // email 검증
        Member member = memberRepository.findUserByEmail(memberCommand.email()).orElseThrow(
                () -> CustomException.of(AuthErrorCode.LOGIN_FAIL)
        );

        // password 검증
        if(!member.getPassword().equals(Member.makePassword(memberCommand.password()))) {
            throw CustomException.of(AuthErrorCode.LOGIN_FAIL);
        }
    }

    @Transactional(readOnly = true)
    public MemberInfo getMyInfo(String email){
        return MemberInfo.from(memberRepository.findUserByEmail(email).orElseThrow(
                () -> CustomException.of(AuthErrorCode.NOT_FOUND)
        ));
    }

    @Transactional
    public void changeNickname(String email, String nickname){
        Member member = memberRepository.findUserByEmail(email).orElseThrow(
                () -> CustomException.of(AuthErrorCode.NOT_FOUND)
        );

        member.changeName(nickname);
    }

    // 관리자용 RD
    @Transactional(readOnly = true)
    public List<MemberInfo> findAll(){
        return memberRepository.findAll().stream()
                .map(MemberInfo::from)
                .toList();
    }

    @Transactional
    public void delete(Long id){
        memberRepository.deleteById(id);
    }
}
