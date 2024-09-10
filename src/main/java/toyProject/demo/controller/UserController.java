package toyProject.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toyProject.demo.DTO.user.UserRequest;
import toyProject.demo.DTO.user.UserResponse;
import toyProject.demo.service.UserService;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/users")
    public ResponseEntity<List<UserResponse>> readUsers(){
        List<UserResponse> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<UserResponse> readUser(@PathVariable Long id){
        UserResponse user = userService.findOne(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/api/users")
    public ResponseEntity<Void> createUser(@RequestBody UserRequest userRequest){
        userService.save(userRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest){
        userService.update(id, userRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
