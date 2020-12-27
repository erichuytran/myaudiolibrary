package com.myaudiolibrary.web.controller;

import com.myaudiolibrary.web.model.Album;
import com.myaudiolibrary.web.repository.AlbumRepository;
import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Controller
@RequestMapping("/albums")
public class AlbumController {

//    @Autowired
//    AlbumRepository albumRepository;
//
//    @PostMapping(value = "", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//    public RedirectView createAlbum(@RequestBody Album album) {
//        if (albumRepository.existsByTitle(album.getTitle())) {
//            // Already exist exception
//        }
//        albumRepository.save(album);
//        return new RedirectView("/artists/" + album.getArtist().getId());
//    }
//
//    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
////    @ResponseStatus(HttpStatus.NO_CONTENT) //204
//    public RedirectView deleteAlbum(@PathVariable Long id) {
//        Optional<Album> album = albumRepository.findById(id);
//        Long artistId = album.get().getArtist().getId();
//        albumRepository.deleteById(id);
//        return new RedirectView("/artists/" + artistId);
//    }

}
