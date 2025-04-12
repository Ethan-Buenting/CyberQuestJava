package cyberquest.User;

import cyberquest.dto.LoginRequest;
//import io.swagger.v3.oas.annotations.Operation;
import cyberquest.dto.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

//    @Operation(summary = "register a new user for CyberQuest")
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        return userService.register(registerRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }

    @GetMapping("/{username}")
    public User getByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }
}