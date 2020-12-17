package com.myaudiolibrary.web.controller;

import com.myaudiolibrary.web.model.Artist;
import com.myaudiolibrary.web.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/artists")
public class ArtistController {

    @Autowired
    ArtistRepository artistRepository;

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

    @GetMapping(value = "", params = "name", produces = MediaType.APPLICATION_JSON_VALUE)
    public String searchByName(final ModelMap model, @RequestParam(value = "name") String name){
        List<Artist> artists = artistRepository.findByNameContainingIgnoreCase(name);
        Integer totalArtists = artists.size();
        model.put("artists", artists);
        model.put("totalArtists", totalArtists);

        return "listeArtists";
    }

}
