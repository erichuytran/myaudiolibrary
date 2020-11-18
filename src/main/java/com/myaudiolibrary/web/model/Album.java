package com.myaudiolibrary.web.model;

import javax.persistence.*;

@Entity
public class Album {

    @ManyToOne
    @JoinColumn(name = "albumId")
    private Artist artist;
    private String title;
    private String id;

    public Album() {

    }

    public Album(Artist artist, String title, String id) {
        this.artist = artist;
        this.title = title;
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
