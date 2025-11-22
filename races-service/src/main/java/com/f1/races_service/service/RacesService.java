package com.f1.races_service.service;

import com.f1.races_service.dto.RaceDTO;
import com.f1.races_service.exception.EntityAlreadyExistsException;
import com.f1.races_service.exception.EntityDoesNotExistException;
import com.f1.races_service.exception.InvalidDateException;
import com.f1.races_service.model.Race;
import com.f1.races_service.model.Track;
import com.f1.races_service.repository.RaceRepository;
import com.f1.races_service.repository.TrackRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RacesService {

    private final RaceRepository raceRepository;
    private final TrackRepository trackRepository;

    public List<Race> getAllRaces(){
        return raceRepository.findAll();
    }

    public Race getRaceById(Integer id){
        return raceRepository.findById(id)
                .orElseThrow(()->new EntityDoesNotExistException("Race with ID " + id + " does not exist"));
    }

    public Race addRace(RaceDTO dto){
        Optional<Race> optionalRace = raceRepository.findByName(dto.getName());
        if(optionalRace.isPresent()){
            throw new EntityAlreadyExistsException("Race with name " + dto.getName() + " already exists");
        }
        if(dto.getDate().isBefore(LocalDate.now())){
            throw new InvalidDateException("Race Weekend date is in the past");
        }
        Optional<Track> optionalTrack = trackRepository.findById(dto.getTrackId());
        if(optionalTrack.isEmpty()){
            throw new EntityDoesNotExistException("Track with id " + dto.getTrackId() + " does not exist");
        }

        Race race = new Race();
        race.setName(dto.getName());
        race.setCountry(dto.getCountry());
        race.setDate(dto.getDate());
        race.setSeason(dto.getSeason());
        race.setTrack(optionalTrack.get());

        return raceRepository.save(race);
    }

    public Race updateRaceTrack(Integer raceId, Integer trackId){
        Race race = raceRepository.findById(raceId)
                .orElseThrow(() -> new EntityDoesNotExistException("Race with ID " + raceId + " does not exist"));

        Track track = trackRepository.findById(trackId)
                .orElseThrow(() -> new EntityDoesNotExistException("Track with ID " + trackId + " does not exist"));

        race.setTrack(track);
        return raceRepository.save(race);
    }

    public void deleteRace(Integer id){
        Race race = raceRepository.findById(id)
                .orElseThrow(() -> new EntityDoesNotExistException("Race with id " + id + " does not exist"));

        raceRepository.delete(race);
    }

    public List<Track> getAllTracks(){
        return trackRepository.findAll();
    }

    public Track getTrackById(Integer id){
        return trackRepository.findById(id)
                .orElseThrow(() -> new EntityDoesNotExistException("Track with id " + id + " does not exist"));
    }

    public Track addTrack(Track track){
        Optional<Track> optionalTrack = trackRepository.findByName(track.getName());
        if(optionalTrack.isPresent()){
            throw new EntityAlreadyExistsException("Track with name " + track.getName() + " already exists");
        }
        return trackRepository.save(track);
    }

    public void deleteTrack(Integer trackId){
        Track track =  trackRepository.findById(trackId)
                .orElseThrow(() -> new EntityDoesNotExistException("Track with id " + trackId + " does not exist"));

        List<Race> races = raceRepository.findAllByTrack(track);
        raceRepository.deleteAll(races);

        trackRepository.delete(track);
    }


}
