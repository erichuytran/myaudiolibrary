package com.myaudiolibrary.web.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Artist {


    @OneToMany(mappedBy = "artist")
    @JsonIgnoreProperties("artist")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Set<Album> albums;
    private String id;

    public Artist() {

    }

    public Artist(Set<Album> albums, String id) {
        this.albums = albums;
        this.id = id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Id
    public String getId() {
        return id;
    }

}
