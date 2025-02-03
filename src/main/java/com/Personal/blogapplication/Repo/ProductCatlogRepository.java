package com.Personal.blogapplication.Repo;

import com.Personal.blogapplication.Entity.Product;
import com.Personal.blogapplication.Entity.ProductCatlog;
import com.Personal.blogapplication.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Repository
public interface ProductCatlogRepository extends JpaRepository<ProductCatlog, Long> {

    List<ProductCatlog> findByCategory(Category category);

    List<ProductCatlog> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    List<ProductCatlog> findByRatingGreaterThanEqual(Double rating);

}
