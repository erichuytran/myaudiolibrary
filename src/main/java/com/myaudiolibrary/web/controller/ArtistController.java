package com.myaudiolibrary.web.controller;

import com.myaudiolibrary.web.model.Artist;
import com.myaudiolibrary.web.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Page<Artist> searchByName(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "20") Integer size,
            @RequestParam(value = "sortDirection", defaultValue = "ASC") Sort.Direction sortDirection,
            @RequestParam(value = "sortProperty", defaultValue = "name") String sortProperty){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortProperty));
        Page<Artist> artists = artistRepository.findByNameContaining(name, pageable);
        return artists;
    }

//    Q3
//    @GetMapping(
//            value = "",
//            params = {"page", "size", "sortProperty", "sortDirection"},
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public Page<Artist> artistPagedList(
//            @RequestParam(value = "page", defaultValue = "0") Integer page,
//            @RequestParam(value = "size", defaultValue = "10") Integer size,
//            @RequestParam(value = "sortProperty", defaultValue = "name") String sortProperty,
//            @RequestParam(value = "sortDirection", defaultValue = "ASC") Sort.Direction sortDirection) {
//        if (page < 0) {
//            throw new IllegalArgumentException("Le paramètre page doit être positif ou nul.");
//        }
//        if (size <= 0 ||size < 50) {
//            throw new IllegalArgumentException("Le paramètre size doit être compris entre 0 et 50.");
//        }
//        if (!"ASC".equalsIgnoreCase(String.valueOf(sortDirection)) && !"DESC".equalsIgnoreCase(String.valueOf(sortDirection))) {
//            throw new IllegalArgumentException("Le paramètre sortDirection doit valoir ASC ou DESC.");
//        }
//        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortProperty));
//        Page<Artist> artistsPage = artistRepository.findAll(pageable);
//        return artistsPage;
//    }


//    }

}
