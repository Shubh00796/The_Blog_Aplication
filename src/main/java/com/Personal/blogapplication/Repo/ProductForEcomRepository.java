package com.Personal.blogapplication.Repo;

import com.Personal.blogapplication.Entity.ProductForEcom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductForEcomRepository extends JpaRepository<ProductForEcom, String> {

//    List<ProductForEcom> findByStock(int stock);
//
//    Optional<ProductForEcom> findByName(String name);
}