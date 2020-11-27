package com.myaudiolibrary.web.repository;

import com.myaudiolibrary.web.model.Artist;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArtistRepository extends CrudRepository<Artist, Long> {
    List<Artist> findByNameContaining(String name);
}
