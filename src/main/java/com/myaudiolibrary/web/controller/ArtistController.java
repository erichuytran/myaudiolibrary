package com.myaudiolibrary.web.controller;

import com.myaudiolibrary.web.model.Artist;
import com.myaudiolibrary.web.repository.AlbumRepository;
import com.myaudiolibrary.web.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/artists")
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
    public String searchByName(final ModelMap model, @RequestParam(value = "name") String name) {
        List<Artist> artists = artistRepository.findByNameContainingIgnoreCase(name);
        Integer totalArtists = artists.size();
        model.put("artists", artists);
        model.put("totalArtists", totalArtists);

        return "listeArtists";
    }

    @GetMapping(
            value = "",
            params = {"page", "size", "sortProperty", "sortDirection"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String artistPagedList(
            final ModelMap model,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sortProperty", defaultValue = "name") String sortProperty,
            @RequestParam(value = "sortDirection", defaultValue = "ASC") Sort.Direction sortDirection) {
        checkParams(page, size, sortDirection);
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortProperty));
        Page<Artist> artistsPage = artistRepository.findAll(pageable);
        model.put("artists", artistsPage);
        model.put("pageNumber", page + 1);
        // IL FAUT DISABLE LE BOUTTON SI ON EST SUR LA PREMIERE PAGE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!s!!!!!!!!!!!!!!!!!
        model.put("previousPage", page - 1);
        model.put("nextPage", page + 1);
        return "listeArtists";
    }

    @GetMapping(value = "/new")
    public String newArtist(final ModelMap model) {
        model.put("artist", new Artist());
        return "detailArtist";
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public RedirectView createArtist(Artist artist) {
        artist = artistRepository.save(artist);
        return new RedirectView("/artists/" + artist.getId());
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public RedirectView saveArtist(Artist artist, @PathVariable Long id) {
        artistRepository.save(artist);
        return new RedirectView("/artists/" + id);
    }

    @DeleteMapping(value = "/{id}")
    @Transactional
//    @ResponseStatus(HttpStatus.NO_CONTENT) //204
    public RedirectView deleteArtist(@PathVariable Long id) {
        Optional<Artist> artist = artistRepository.findById(id);
        if(artist.isEmpty()) {
            // Generer une erreur
        }
        albumRepository.deleteByArtist(artist);
        artistRepository.deleteById(id);
        return new RedirectView("/artists?page=0&size=10&sortProperty=name&sortDirection=ASC");
    }

}
