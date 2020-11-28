package com.myaudiolibrary.web.repository;

import com.myaudiolibrary.web.model.Artist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface ArtistRepository extends CrudRepository<Artist, Long> {
    Page<Artist> findByNameContaining(String name, Pageable pageable);

    Boolean existsByName(String name);

    Page<Artist> findAll(Pageable pageable);

}
