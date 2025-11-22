package com.f1.races_service.controller;

import com.f1.races_service.dto.RaceDTO;
import com.f1.races_service.model.Race;
import com.f1.races_service.model.Track;
import com.f1.races_service.service.RacesService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.ws.rs.Path;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class RacesController {

    private final RacesService racesService;

    @GetMapping("/races")
    @ResponseStatus(HttpStatus.OK)
    public List<Race> getAllRaces(){
        return racesService.getAllRaces();
    }

    @GetMapping("/races/{id}")
    public Race getRaceById(@PathVariable Integer id){
        return racesService.getRaceById(id);
    }

    @PostMapping("/races/add")
    public ResponseEntity<Race> addRace(@Valid @RequestBody RaceDTO race){
        return new ResponseEntity<>(racesService.addRace(race), HttpStatus.CREATED);
    }

    @DeleteMapping("/races/{raceId}")
    public void deleteRace(@PathVariable("raceId") @Min(1) Integer raceId){
        racesService.deleteRace(raceId);
    }

    @PatchMapping("/races/{raceId}/track/{trackId}")
    public Race updateRace(@PathVariable @Min(1) Integer raceId, @PathVariable @Min(1)  Integer trackId){
        return racesService.updateRaceTrack(raceId, trackId);
    }

    @GetMapping("/tracks")
    @ResponseStatus(HttpStatus.OK)
    public List<Track> getAllTracks(){
        return racesService.getAllTracks();
    }

    @GetMapping("/tracks/{id}")
    public Track getTrackById(@PathVariable Integer id){
        return racesService.getTrackById(id);
    }

    @PostMapping("/tracks/add")
    public ResponseEntity<Track> addTrack(@Valid @RequestBody Track track){
        return new ResponseEntity<>(racesService.addTrack(track), HttpStatus.CREATED);
    }

    @DeleteMapping("/tracks/{trackId}")
    public void deleteTrack(@PathVariable("trackId") @Min(1) Integer trackId){
        racesService.deleteTrack(trackId);
    }
}
