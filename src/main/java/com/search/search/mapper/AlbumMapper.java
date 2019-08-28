package com.search.search.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.search.search.model.Album;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AlbumMapper {
    public Album mapAlbum(String jsonString) throws IOException {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.readTree(jsonString);
        } catch (IOException e) {
            throw new IOException("JSON not valid " + e);
        }
        return new ObjectMapper().readValue(jsonString, Album.class);
    }
}
