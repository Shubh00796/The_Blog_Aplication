package com.Personal.blogapplication.Repo;

import com.Personal.blogapplication.Entity.Driver;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    @Query(value = "SELECT * FROM driver " +
            "WHERE ST_DWithin(current_location, ST_GeomFromText(:location, 4326), :distance) " +
            "AND available = true", nativeQuery = true)
    List<Driver> findByAvailableTrueAndCurrentLocationNear(
            @Param("location") String location,
            @Param("distance") double distance
    );
}
