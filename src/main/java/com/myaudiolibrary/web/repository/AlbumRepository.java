package com.myaudiolibrary.web.repository;

import com.myaudiolibrary.web.model.Album;
import com.myaudiolibrary.web.model.Artist;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AlbumRepository extends CrudRepository<Album, Long> {

    void deleteByArtist(Optional<Artist> artist);

    Boolean existsByTitle(String title);
}