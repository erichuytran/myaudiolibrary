package com.myaudiolibrary.web.controller;

import com.myaudiolibrary.web.model.Artist;
import com.myaudiolibrary.web.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/artists")
public class ArtistController {

    @Autowired
    ArtistRepository artistRepository;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Artist> getArtist(@PathVariable(value = "id") Long id) {
        Optional<Artist> artist = artistRepository.findById(id);
        return artist;
    }
}
