package EasiestCV.easiestCV.controller;

import EasiestCV.easiestCV.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}

