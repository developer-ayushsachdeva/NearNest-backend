package com.nearnest.backend.controller;

import com.nearnest.backend.entity.Ping;
import com.nearnest.backend.service.PingService;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import java.util.List;
import com.nearnest.backend.dto.PingResponse;

@RestController
@RequestMapping("/api/pings")
public class PingController {

    private final PingService pingService;

    public PingController(PingService pingService) {
        this.pingService = pingService;
    }

    @PostMapping
    public Ping createPing(
            @RequestBody Ping ping,
            Authentication authentication
    ) {
        Long userId = (Long) authentication.getCredentials();

        return pingService.createPing(ping, userId);
    }

    @GetMapping
    public List<Ping> getAllPings() {
        return pingService.getAllPings();
    }

    @GetMapping("/nearby")
    public List<PingResponse> getNearbyPings(
            @RequestParam double lat,
            @RequestParam double lng
    ) {
        return pingService.getNearbyPings(lat, lng);
    }

    @PostMapping("/{id}/like")
    public Ping likePing(@PathVariable Long id) {
        return pingService.likePing(id);
    }

    @PostMapping("/{id}/confirm")
    public Ping confirmPing(@PathVariable Long id) {
        return pingService.confirmPing(id);
    }

    @PostMapping("/{id}/dispute")
    public Ping disputePing(@PathVariable Long id) {
        return pingService.disputePing(id);
    }

    @PostMapping("/{id}/share")
    public Ping sharePing(@PathVariable Long id) {
        return pingService.sharePing(id);
    }

    @GetMapping("/{id}")
    public Ping getPingById(@PathVariable Long id) {
        return pingService.getPingById(id);
    }

}