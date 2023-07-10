package EasiestCV.easiestCV.controller;

import EasiestCV.easiestCV.dto.LoginInputData;
import EasiestCV.easiestCV.dto.UserDTO;
import EasiestCV.easiestCV.model.User;
import EasiestCV.easiestCV.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@Tag(name = "User Controller")
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 웹 요청 처리 메서드들
    @Operation(summary = "Get user by id", description = "Provide an id to lookup specific user from the user database")
    @GetMapping("/userinfo")
    public ResponseEntity<?> getUsername(@RequestParam(name="userid") String userid){
        try{
            return new ResponseEntity<>(userService.getUserById(userid), HttpStatus.OK);
        }
        catch (NoSuchElementException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
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

    @Operation(summary = "Log in", description = "Get authentication and go to /userid/admin")
    @PostMapping("/login")
    public ResponseEntity<?> loginToAdmin(@RequestBody LoginInputData loginData){
        try{
            UserDTO userDTO = userService.login(loginData.getUserid(), loginData.getPassword());
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }



}

