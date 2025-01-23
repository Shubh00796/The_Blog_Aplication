package com.Personal.blogapplication.Controller;

import com.Personal.blogapplication.Dtos.UserDTO;
import com.Personal.blogapplication.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUserByName(@PathVariable String username){
        Optional<UserDTO> dto = userService.findByUsername(username);
        if(dto.isPresent()){
            return ResponseEntity.ok(dto.get());

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(null); // Or you can return a custom error response
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        System.out.println("Received UserDTO: " + userDTO);
        try {
            UserDTO savedUser = userService.saveUser(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


}
