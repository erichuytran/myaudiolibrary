package com.myaudiolibrary.web.controller;

import com.myaudiolibrary.web.model.Artist;
import com.myaudiolibrary.web.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/artists")
public class ArtistController {

    @Autowired
    ArtistRepository artistRepository;

//    Q1
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Artist> getArtist(@PathVariable(value = "id") Long id) {
        Optional<Artist> artist = artistRepository.findById(id);
        if(artist.isEmpty()) {
            throw new EntityNotFoundException("L'artiste d'identifiant " + id + " n'a pas été trouvé.");
    }
        return artist;
    }

//    Q2
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Artist> searchByName(@RequestParam(value = "name") String name) {
        List<Artist> artistList = artistRepository.findByNameContaining(name);
        return artistList;
    }

}
