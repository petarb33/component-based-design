package com.f1.races_service.repository;

import com.f1.races_service.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrackRepository extends JpaRepository<Track,Integer> {
    Optional<Track> findByName(String name);
}
