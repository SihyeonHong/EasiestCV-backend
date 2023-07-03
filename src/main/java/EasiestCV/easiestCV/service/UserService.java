package EasiestCV.easiestCV.service;

import EasiestCV.easiestCV.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 비즈니스 로직 메서드들
    public String getUsernameById(String userid){
        if(userid.equals("testid")){
            return "testname";
        } else {
            throw new NoSuchElementException("No user found with the provided ID.");
        }
    }
}

