package io.paws.paws.service;

import io.paws.paws.entity.User;
import io.paws.paws.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void createUser(User user) {
        userRepository.save(user);
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public User updateUser(String id, User updatedUser) {
        User existingUser = findById(id);

        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setName(updatedUser.getName());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setTel(updatedUser.getTel());
        return userRepository.save(existingUser);
    }

    private User findById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }
}
