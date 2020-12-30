package com.myaudiolibrary.web.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Artist {

    @OneToMany(mappedBy = "artist", fetch=FetchType.EAGER)
    @JsonIgnoreProperties("artist")
    private Set<Album> albums;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ArtistId")
    private Long id;

    @Column(name = "Name")
    private String name;

    public Artist() {

    }

    public Artist(Set<Album> albums, Long id, String name) {
        this.albums = albums;
        this.id = id;
        this.name = name;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return
                id.equals(artist.id) &&
                name.equals(artist.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Artist{" +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}