package EasiestCV.easiestCV.controller;

import EasiestCV.easiestCV.model.User;
import EasiestCV.easiestCV.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User Controller")
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 웹 요청 처리 메서드들
    @Operation(summary = "Get username by id", description = "Provide an id to lookup specific user from the user database")
    @GetMapping("/username")
    public String getUsername(@RequestParam(name="userid") String userid){
        return userService.getUsernameById(userid);
    }

    @Operation(summary = "Register a new user", description = "Provide user details to register a new user")
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        // ResponseEntity<?>를 사용하면, 성공한 경우와 실패한 경우에 다른 타입의 응답 본문을 반환할 수 있습니다.
        try {
            User savedUser = userService.signup(user);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}

