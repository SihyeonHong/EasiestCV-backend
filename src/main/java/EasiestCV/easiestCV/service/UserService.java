package EasiestCV.easiestCV.service;

import EasiestCV.easiestCV.dto.UserDTO;
import EasiestCV.easiestCV.model.User;
import EasiestCV.easiestCV.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // 비즈니스 로직 메서드들
    public Optional<User> getUserById(String userid){
        if(userRepository.existsById(userid)){
            return userRepository.findByUserid(userid);
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

    public UserDTO login(String userid, String password){
        User user = userRepository.findByUserid(userid)
                .filter(it -> passwordEncoder.matches(password, it.getPassword()))
                .orElseThrow(() -> new IllegalArgumentException("Invalid ID or password."));
        String token = jwtTokenProvider.createToken(String.format("%s:%s:%s:%s:%s", user.getUserid(), user.getUsername(), user.getEmail(), user.getImg(), user.getPdf()));
        return new UserDTO(user, token);
    }

}

