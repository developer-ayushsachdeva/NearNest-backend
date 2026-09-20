package com.nearnest.backend.repository;

import com.nearnest.backend.entity.Ping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PingRepository extends JpaRepository<Ping, Long> {

    List<Ping> findByExpiresAtAfter(LocalDateTime currentTime);
}