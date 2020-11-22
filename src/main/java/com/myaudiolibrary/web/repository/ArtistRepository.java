package com.myaudiolibrary.web.repository;

import com.myaudiolibrary.web.model.Artist;
import org.springframework.data.repository.CrudRepository;

public interface ArtistRepository extends CrudRepository<Artist, Long> {

}
