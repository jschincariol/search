package com.search.search.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Album {
    List<ItemAlbum> results;

    public Album() {
    }

    public Album(List<ItemAlbum> results) {
        this.results = results;
    }

    public List<ItemAlbum> getResults() {
        return results;
    }

    public void setResults(List<ItemAlbum> results) {
        this.results = results;
    }
}
