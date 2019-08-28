package com.search.search.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemAlbum {
    private String artistName;
    private String collectionName;
    private String primaryGenreName;

    public ItemAlbum() {
    }

    public ItemAlbum(String artistName, String collectionName, String primaryGenreName) {
        this.artistName = artistName;
        this.collectionName = collectionName;
        this.primaryGenreName = primaryGenreName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getPrimaryGenreName() {
        return primaryGenreName;
    }

    public void setPrimaryGenreName(String primaryGenreName) {
        this.primaryGenreName = primaryGenreName;
    }
}
