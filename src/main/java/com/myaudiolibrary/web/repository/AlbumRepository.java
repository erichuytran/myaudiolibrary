package com.myaudiolibrary.web.repository;

import com.myaudiolibrary.web.model.Album;
import org.springframework.data.repository.CrudRepository;

public interface AlbumRepository extends CrudRepository<Album, Long> {

}
