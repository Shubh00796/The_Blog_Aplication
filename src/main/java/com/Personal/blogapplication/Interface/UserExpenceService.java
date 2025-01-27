package com.Personal.blogapplication.Interface;

import com.Personal.blogapplication.Dtos.UserForExpenceDTO;
import com.Personal.blogapplication.Entity.UserForExpence;
import com.Personal.blogapplication.Exceptions.UserRegistrationException;

public interface UserExpenceService {
    UserForExpence registerUser(UserForExpenceDTO userForExpenceDTO) throws UserRegistrationException;
    String authenticateUser(String username, String password);
}