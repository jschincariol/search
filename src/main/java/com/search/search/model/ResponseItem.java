package com.search.search.model;

public class ResponseItem implements Comparable< ResponseItem >{

    private String artistAuthor;
    private String title;
    private String description;

    public ResponseItem() {
    }

    public ResponseItem(String artistAuthor, String title, String description) {
        this.artistAuthor = artistAuthor;
        this.title = title;
        this.description = description;
    }

    public String getArtistAuthor() {
        return artistAuthor;
    }

    public void setArtistAuthor(String artistAuthor) {
        this.artistAuthor = artistAuthor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int compareTo(ResponseItem o) {
        return this.getTitle().compareTo(o.getTitle());
    }
}
