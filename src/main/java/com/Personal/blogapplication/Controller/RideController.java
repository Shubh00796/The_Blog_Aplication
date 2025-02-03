package com.Personal.blogapplication.Controller;

import com.Personal.blogapplication.Dtos.RideDTO;
import com.Personal.blogapplication.Service.RideService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rides")
@RequiredArgsConstructor
public class RideController {
    private final RideService rideService;

    @PostMapping("/request")
    public ResponseEntity<RideDTO> requestRide(@RequestBody RideDTO rideDTO) {
        return ResponseEntity.ok(rideService.requestRide(rideDTO));
    }

    @PostMapping("/{rideId}/match")
    public ResponseEntity<RideDTO> matchDriver(@PathVariable Long rideId) {
        return ResponseEntity.ok(rideService.matchDriver(rideId));
    }

    @PatchMapping("/{rideId}/status")
    public ResponseEntity<RideDTO> updateRideStatus(@PathVariable Long rideId, @RequestParam String status) {
        return ResponseEntity.ok(rideService.updateRideStatus(rideId, status));
    }

    @DeleteMapping("/{rideId}")
    public ResponseEntity<String> cancelRide(@PathVariable Long rideId) {
        rideService.cancelRide(rideId);
        return ResponseEntity.ok("Ride cancelled successfully");
    }

    @PostMapping("/{rideId}/payment")
    public ResponseEntity<RideDTO> simulatePayment(@PathVariable Long rideId) {
        return ResponseEntity.ok(rideService.simulatePayment(rideId));
    }
}
