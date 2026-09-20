package com.nearnest.backend.service;

import com.nearnest.backend.entity.Ping;
import com.nearnest.backend.repository.PingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.nearnest.backend.dto.PingResponse;
import com.nearnest.backend.entity.User;
import com.nearnest.backend.repository.UserRepository;

@Service
public class PingService {

    private final PingRepository pingRepository;
    private final UserRepository userRepository;

    public PingService(
            PingRepository pingRepository,
            UserRepository userRepository
    ) {
        this.pingRepository = pingRepository;
        this.userRepository = userRepository;
    }

    public Ping createPing(Ping ping, Long userId) {
        ping.setUserId(userId);
        ping.setCreatedAt(LocalDateTime.now());
        return pingRepository.save(ping);
    }

    public List<Ping> getAllPings() {
        return pingRepository.findAll();
    }

    public Ping getPingById(Long pingId) {
        Ping ping = pingRepository.findById(pingId)
                .orElseThrow(() -> new RuntimeException("Ping not found"));

        if (ping.getExpiresAt() == null || !ping.getExpiresAt().isAfter(LocalDateTime.now())) {
            throw new RuntimeException("Ping has expired");
        }

        return ping;
    }

    public List<PingResponse> getNearbyPings(
            double userLatitude,
            double userLongitude
    ) {
        List<Ping> activePings =
                pingRepository.findByExpiresAtAfter(LocalDateTime.now());

        List<PingResponse> nearbyPings = new ArrayList<>();

        for (Ping ping : activePings) {

            double distance = calculateDistance(
                    userLatitude,
                    userLongitude,
                    ping.getLatitude(),
                    ping.getLongitude()
            );

            if (distance <= ping.getRadiusKm()) {
                nearbyPings.add(toPingResponse(ping));
            }
        }

        return nearbyPings;
    }

    public Ping likePing(Long pingId) {
        Ping ping = pingRepository.findById(pingId)
                .orElseThrow(() -> new RuntimeException("Ping not found"));

        ping.setLikesCount(ping.getLikesCount() + 1);

        return pingRepository.save(ping);
    }

    public Ping confirmPing(Long pingId) {
        Ping ping = pingRepository.findById(pingId)
                .orElseThrow(() -> new RuntimeException("Ping not found"));

        ping.setConfirmationsCount(ping.getConfirmationsCount() + 1);

        return pingRepository.save(ping);
    }

    public Ping disputePing(Long pingId) {
        Ping ping = pingRepository.findById(pingId)
                .orElseThrow(() -> new RuntimeException("Ping not found"));

        ping.setDisputesCount(ping.getDisputesCount() + 1);

        return pingRepository.save(ping);
    }

    public Ping sharePing(Long pingId) {
        Ping ping = pingRepository.findById(pingId)
                .orElseThrow(() -> new RuntimeException("Ping not found"));

        ping.setSharesCount(ping.getSharesCount() + 1);

        return pingRepository.save(ping);
    }


    private double calculateDistance(
            double lat1,
            double lon1,
            double lat2,
            double lon2
    ) {
        final double EARTH_RADIUS_KM = 6371.0;

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        double a =
                Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                        + Math.cos(Math.toRadians(lat1))
                        * Math.cos(Math.toRadians(lat2))
                        * Math.sin(lonDistance / 2)
                        * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS_KM * c;
    }
    private PingResponse toPingResponse(Ping ping) {

        User user = userRepository.findById(ping.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new PingResponse(
                ping,
                user.getName()
        );
    }
}