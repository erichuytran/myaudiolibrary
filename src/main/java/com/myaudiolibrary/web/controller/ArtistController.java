package com.myaudiolibrary.web.controller;

import com.myaudiolibrary.web.model.Artist;
import com.myaudiolibrary.web.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Controller
@RequestMapping("/artists")
public class ArtistController {

    @Autowired
    ArtistRepository artistRepository;

    @GetMapping(value = "/{id}")
    public String getArtistById(@PathVariable Long id, final ModelMap model) {
        Optional<Artist> artistOptionnal = artistRepository.findById(id);

        //Gestion de l'erreur 404 ///////////////////////// TROUVER COMMENT AFFICHER LE MESSAGE CORRECTEMENT
        if (artistOptionnal.isEmpty()) {
            throw new EntityNotFoundException("L'artiste d'identifiant " + id + " n'a pas été trouvé");
        }

        model.put("artist", artistOptionnal.get());
        return "detailArtist";
    }

}
