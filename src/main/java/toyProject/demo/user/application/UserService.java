package toyProject.demo.user.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyProject.demo.user.application.dto.UserCommand;
import toyProject.demo.user.application.dto.UserInfo;
import toyProject.demo.user.presentation.dto.UserRequest;
import toyProject.demo.user.presentation.dto.UserResponse;
import toyProject.demo.user.domain.User;
import toyProject.demo.user.persistence.UserRepository;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<UserResponse> findAll(){
        return userRepository.findAll().stream().map(UserResponse::from).toList();
    }

    @Transactional(readOnly = true)
    public UserResponse findOneUser(Long id){
        return UserResponse.from(userRepository.findById(id).orElseThrow(NoSuchFieldError::new));
    }

    @Transactional
    public void save(UserCommand userCommand){
        userRepository.save(UserCommand.toEntityFrom(userCommand));
    }

    @Transactional
    public void update(Long id, UserRequest userRequest){
        User user = userRepository.findById(id).orElseThrow(NoSuchFieldError::new);
        user.changeName(userRequest.getNickname());
    }

    @Transactional
    public void delete(Long id){
        userRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public boolean isExistedUser(String email){
        return userRepository.existsByEmail(email);
    }

    @Transactional(readOnly = true)
    public UserInfo loginUser(String email){
        return UserInfo.from(userRepository.findUserByEmail(email).orElseThrow(NoSuchFieldError::new));
    }

    @Transactional(readOnly = true)
    public boolean isLogin(UserCommand userCommand){
        UserInfo userInfo = loginUser(userCommand.getEmail());
        if(!userInfo.getNickname().equals(userCommand.getNickname())) {
            return false;
        }
        if(!userInfo.getPassword().equals(userCommand.getPassword())) {
            return false;
        }

        return true;
    }
}
