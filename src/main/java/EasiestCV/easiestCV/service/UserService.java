package EasiestCV.easiestCV.service;

import EasiestCV.easiestCV.model.User;
import EasiestCV.easiestCV.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 비즈니스 로직 메서드들
    public String getUsernameById(String userid){
        if(userid.equals("testid")){
            return "testname";
        } else {
            throw new NoSuchElementException("No user found with the provided ID.");
        }
    }

    public User signup(User user){
        // check if a user with the same ID already exists
        if(userRepository.findByUserid(user.getUserid()).isPresent()){
            throw new RuntimeException("A user with the same ID already exists.");
        }

        // encode the user's password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // save the new user to the database
        User savedUser = userRepository.save(user);

        // create new user to mysql database
        return savedUser;
    }
}

