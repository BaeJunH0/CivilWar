package toyProject.demo.user.application;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import toyProject.demo.user.presentation.dto.UserRequest;
import toyProject.demo.user.presentation.dto.UserResponse;
import toyProject.demo.user.domain.User;
import toyProject.demo.user.persistence.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponse> findAll(){
        List<UserResponse> answer = new ArrayList<>();
        List<User> users = userRepository.findAll();
        for (User user : users) {
            answer.add(new UserResponse(user));
        }
        return answer;
    }

    public UserResponse findOne(Long id){
        User user = userRepository.findById(id).orElseThrow(NoSuchFieldError::new);
        return new UserResponse(user);
    }

    @Transactional
    public void save(UserRequest userRequest){
        userRepository.save(new User(
                userRequest.getEmail(), userRequest.getPassword(), userRequest.getNickname()
        ));
    }

    @Transactional
    public void update(Long id, UserRequest userRequest){
        User user = userRepository.findById(id).orElseThrow(NoSuchFieldError::new);
        user.changeName(userRequest.getNickname());
        // 추가 예정
    }

    @Transactional
    public void delete(Long id){
        userRepository.deleteById(id);
    }
}
