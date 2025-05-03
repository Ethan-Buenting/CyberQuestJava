package cyberquest.User;

import cyberquest.User.User;
import cyberquest.User.UserRepository;
import cyberquest.dto.LoginRequest;
import cyberquest.dto.ModuleRequest;
import cyberquest.dto.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public ResponseEntity<String> register(RegisterRequest registerRequest) {
        User user = userRepository.findByUsername(registerRequest.getUsername());
        if (user != null) {
            return ResponseEntity.status(409).body("Username already in use");
        }
        user = new User(registerRequest.getName(), registerRequest.getUsername(), registerRequest.getPassword());
        userRepository.save(user);
        return ResponseEntity.ok("User created Successfully");
    }

    public ResponseEntity<String> login(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername());
        if (user == null) {
            return ResponseEntity.status(401).body("User not found");
        }
        if (!user.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.status(401).body("Incorrect Password");
        }
        return ResponseEntity.ok("Login Successful");
    }

    public ResponseEntity<String> updateModuleCompletion(ModuleRequest moduleRequest) {
        User user = userRepository.findByUsername(moduleRequest.getUsername());
        if (user == null) {
            return ResponseEntity.status(401).body("User not found");
        }
        if (moduleRequest.getModuleNum() < 0 || moduleRequest.getModuleNum() > 8) {
            return ResponseEntity.status(401).body("Module number out of range");
        }
        StringBuilder newProgress = new StringBuilder(user.getModuleProgress());
        newProgress.setCharAt(moduleRequest.getModuleNum(), '1');
        user.setModuleProgress(newProgress.toString());
        userRepository.save(user);
        return ResponseEntity.ok("Module Updated Successfully");
    }
}
