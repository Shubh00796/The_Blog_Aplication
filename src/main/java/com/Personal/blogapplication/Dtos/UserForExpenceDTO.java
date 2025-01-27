package com.Personal.blogapplication.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserForExpenceDTO {

    private String username;
    private String password;
    private String email;
}
