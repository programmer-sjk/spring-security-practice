package com.example.security.user;

import com.example.security.user.domain.MyUserPrincipal;
import com.example.security.user.domain.User;
import com.example.security.user.dto.UserRequest;
import com.example.security.user.dto.UserResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse find(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        return new UserResponse(user);
    }

    public List<UserResponse> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void signUp(UserRequest request) {
        String encryptPassword = passwordEncoder.encode(request.getPassword());
        userRepository.save(request.toEntity(encryptPassword));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("사용자가 존재하지 않습니다.");
        }
        return new MyUserPrincipal(user);
    }
}
