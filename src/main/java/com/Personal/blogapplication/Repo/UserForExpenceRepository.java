package com.Personal.blogapplication.Repo;

import com.Personal.blogapplication.Entity.UserForExpence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserForExpenceRepository extends JpaRepository<UserForExpence, Long> {
    Optional<UserForExpence> findByUsername(String username);
    Optional<UserForExpence> findByEmail(String email);




}
