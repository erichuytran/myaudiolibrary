package com.myaudiolibrary.web.controller;

import com.myaudiolibrary.web.model.Album;
import com.myaudiolibrary.web.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceAlreadyExistsException;

@RestController
@RequestMapping(value = "/albums")
public class AlbumController {

    @Autowired
    AlbumRepository albumRepository;

//    Q7
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Album createAlbum(@RequestBody Album album) throws InstanceAlreadyExistsException {
        if(albumRepository.existsByTitle(album.getTitle())) {
            throw new InstanceAlreadyExistsException("L'album " + album.getTitle() + " de " + album.getArtist() + "existe déjà.");
        }
        return albumRepository.save(album);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) //204
     public void deleteAlbum(@PathVariable("id") Long id) {
        albumRepository.deleteById(id);
    }

}
