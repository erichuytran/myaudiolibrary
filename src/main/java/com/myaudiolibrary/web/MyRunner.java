package com.myaudiolibrary.web;

import java.util.Set;

import com.myaudiolibrary.web.model.Album;
import com.myaudiolibrary.web.model.Artist;
import com.myaudiolibrary.web.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner {

    @Autowired
    ArtistRepository artistRepository;

    @Override
    public void run(String... strings) throws Exception {
        Iterable<Artist> artists = artistRepository.findAll();
        for (Artist artist : artists) {
            System.out.println(artist.toString());
        }

//        for (Artist artist : artists) {
//            Set<Album> albums = artist.getAlbums();
//            print(artist.getName());
//            for (Album album : albums) {
//                print(album.getTitle());
//            }
//        }
    }

    public static void print(Object t) {
        System.out.println(t);
    }
}