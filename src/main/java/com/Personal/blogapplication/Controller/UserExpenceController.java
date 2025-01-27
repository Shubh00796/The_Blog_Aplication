package com.Personal.blogapplication.Controller;

import com.Personal.blogapplication.Dtos.UserForExpenceDTO;
import com.Personal.blogapplication.Entity.UserForExpence;
import com.Personal.blogapplication.Exceptions.UserRegistrationException;
import com.Personal.blogapplication.Interface.UserExpenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user_expence")
@RequiredArgsConstructor
public class UserExpenceController {

    private final UserExpenceService userExpenceService;

    @PostMapping("/sign-up")
        public ResponseEntity<?> registerUser(@RequestBody UserForExpenceDTO userForExpenceDTO) throws UserRegistrationException {
        UserForExpence userForExpence = userExpenceService.registerUser(userForExpenceDTO);
        return ResponseEntity.ok(userForExpence);

    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserForExpenceDTO userForExpenceDTO){
        String result = userExpenceService.authenticateUser(userForExpenceDTO.getUsername(), userForExpenceDTO.getPassword());
        return ResponseEntity.ok(result);

    }
}
