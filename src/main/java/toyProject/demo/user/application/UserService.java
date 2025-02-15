package toyProject.demo.user.application;

import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyProject.demo.user.application.dto.UserCommand;
import toyProject.demo.user.application.dto.UserInfo;
import toyProject.demo.user.presentation.dto.UserRequest;
import toyProject.demo.user.presentation.dto.UserResponse;
import toyProject.demo.user.domain.User;
import toyProject.demo.user.persistence.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void makeNewUser(UserCommand userCommand){
        if(isExistedUser(userCommand.email())){
            throw new DuplicateRequestException("이미 존재하는 이메일입니다");
        }
        userRepository.save(UserCommand.toEntityFrom(userCommand));
    }

    @Transactional(readOnly = true)
    public boolean isLogin(UserCommand userCommand){
        if(!isExistedUser(userCommand.email())){
            User savedUser = userRepository.findUserByEmail(userCommand.email()).orElseThrow(NoSuchFieldError::new);
            User checkUser = User.of(userCommand.email(), userCommand.password(), userCommand.nickname());

            if(savedUser.passCheck(checkUser)){
                return true;
            }
            return false;
        }
        return false;
    }

    @Transactional(readOnly = true)
    public boolean isExistedUser(String email){
        return userRepository.existsByEmail(email);
    }

    @Transactional(readOnly = true)
    public UserInfo loginUser(String email){
        return UserInfo.from(userRepository.findUserByEmail(email).orElseThrow(NoSuchFieldError::new));
    }

    // 관리자용 CRUD
    @Transactional(readOnly = true)
    public List<UserInfo> findAll(){
        return userRepository.findAll().stream()
                .map(UserInfo::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public UserInfo findOneUser(Long id){
        return UserInfo.from(userRepository.findById(id).orElseThrow(NoSuchFieldError::new));
    }

    @Transactional
    public void update(Long id, UserRequest userRequest){
        User user = userRepository.findById(id).orElseThrow(NoSuchFieldError::new);
        user.changeName(userRequest.nickname());
    }

    @Transactional
    public void delete(Long id){
        userRepository.deleteById(id);
    }
}
