package io.paws.paws.controller;

import io.paws.paws.DTO.SignupResponseDTO;
import io.paws.paws.DTO.SigninRequestDTO;
import io.paws.paws.DTO.SigninResponseDTO;
import io.paws.paws.DTO.SignupRequestDTO;
import io.paws.paws.entity.User;
import io.paws.paws.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

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

        SignupResponseDTO responseDTO = new SignupResponseDTO(
                user.getEmail()
        );
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SigninRequestDTO request) {
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
                return ResponseEntity.ok(responseDTO);
            } else throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid password");

        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid email or password");
    }
}
