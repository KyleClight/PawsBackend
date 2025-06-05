package io.paws.paws.controller;

import io.paws.paws.DTO.SignupResponseDTO;
import io.paws.paws.DTO.SigninRequestDTO;
import io.paws.paws.DTO.SigninResponseDTO;
import io.paws.paws.DTO.SignupRequestDTO;
import io.paws.paws.entity.User;
import io.paws.paws.service.UserService;
import io.paws.paws.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JWTUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequestDTO request) {
        UserService userService = this.userService;


        Optional<User> existingUser = userService.findUserByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already in use");
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        userService.createUser(user);

        SignupResponseDTO responseDTO = new SignupResponseDTO(user.getEmail());
        String token = jwtUtil.generateToken(user.getEmail());
        return ResponseEntity.ok(Map.of("token", token, "user", responseDTO));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SigninRequestDTO request, JWTUtil jwtUtil, User user) {
        UserService userService = this.userService;

        Optional<User> existingUser = userService.findUserByEmail(request.getEmail());
        if (existingUser.isPresent() && request.getPassword() != null) {

            if (existingUser.get().getPassword().equals(request.getPassword())) {
                User userResponse = existingUser.get();
                SigninResponseDTO responseDTO = new SigninResponseDTO(
                        userResponse.getEmail(),
                        userResponse.getName(),
                        userResponse.getTel(),
                        userResponse.getImageUrl()
                );
                String token = jwtUtil.generateToken(existingUser.get().getEmail());
                return ResponseEntity.ok(Map.of("token", token, "user", responseDTO));
            } else throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid password");

        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid email or password");
    }
}
