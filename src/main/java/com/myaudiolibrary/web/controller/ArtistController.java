package com.myaudiolibrary.web.controller;

import com.myaudiolibrary.web.model.Artist;
import com.myaudiolibrary.web.repository.AlbumRepository;
import com.myaudiolibrary.web.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceAlreadyExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@RestController
@RequestMapping(value = "/artists")
public class ArtistController {

    @Autowired
    ArtistRepository artistRepository;

    @Autowired
    AlbumRepository albumRepository;

    public void checkParams(Integer page, Integer size, Sort.Direction sortDirection) {
        if (page < 0) {
            throw new IllegalArgumentException("Le paramètre page doit être positif ou nul.");
        }
        if (size < 0 || size > 50) {
            throw new IllegalArgumentException("Le paramètre size doit être compris entre 0 et 50.");
        }
        if (!"ASC".equalsIgnoreCase(String.valueOf(sortDirection)) && !"DESC".equalsIgnoreCase(String.valueOf(sortDirection))) {
            throw new IllegalArgumentException("Le paramètre sortDirection doit valoir ASC ou DESC.");
        }
    }

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
    @GetMapping(value = "",
            params = {"name", "page", "size", "sortDirection", "sortProperty"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Artist> searchByName(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "20") Integer size,
            @RequestParam(value = "sortDirection", defaultValue = "ASC") Sort.Direction sortDirection,
            @RequestParam(value = "sortProperty", defaultValue = "name") String sortProperty){
        checkParams(page, size, sortDirection);
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortProperty));
        Page<Artist> artists = artistRepository.findByNameContaining(name, pageable);
        return artists;
    }

//    Q3
    @GetMapping(
            value = "",
            params = {"page", "size", "sortProperty", "sortDirection"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Artist> artistPagedList(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sortProperty", defaultValue = "name") String sortProperty,
            @RequestParam(value = "sortDirection", defaultValue = "ASC") Sort.Direction sortDirection) {
        checkParams(page, size, sortDirection);
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortProperty));
        Page<Artist> artistsPage = artistRepository.findAll(pageable);
        return artistsPage;
    }

//    Q4
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Artist createArtist(@RequestBody Artist artist) throws InstanceAlreadyExistsException {
        if(artistRepository.existsByName(artist.getName())) {
            throw new InstanceAlreadyExistsException("L'artiste " + artist.getName() + " existe déjà.");
        }
        return artistRepository.save(artist);
    }

//    Q5
    @PutMapping(value = "/{id}")
    public Artist updateArtist(@PathVariable("id") Long id, @RequestBody Artist artist) {
        if (!artistRepository.existsById(id)) {
            throw new EntityNotFoundException("L'artiste d'identifiant " + id + " n'existe pas.");
        }
        if (!id.equals(artist.getId())) {
            throw new IllegalArgumentException("Requete invalide.");
        }
        return artistRepository.save(artist);
    }

//    Q6
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) //204
    public void deleteArtist(@PathVariable("id") Long id) {
        Optional<Artist> artist = artistRepository.findById(id);
        albumRepository.deleteAllByArtist(artist);
        artistRepository.deleteById(id);
    }
}
