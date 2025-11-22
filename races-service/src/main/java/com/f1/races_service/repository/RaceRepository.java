package com.f1.races_service.repository;

import com.f1.races_service.model.Race;
import com.f1.races_service.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RaceRepository extends JpaRepository<Race,Integer> {
    Optional<Race> findByName(String name);
    List<Race> findAllByTrack(Track track);
}
