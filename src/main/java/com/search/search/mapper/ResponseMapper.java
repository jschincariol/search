package com.search.search.mapper;

import com.search.search.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ResponseMapper {

    public Response mapResponse(Album album, Book book) {
        List<ResponseItem> responseItems = mapAlbum(album);
        responseItems.addAll(mapBook(book));
        Response response = new Response();
        response.setResponseItemList(responseItems);
        return response;
    }

    private List<ResponseItem> mapAlbum(Album album) {
        Response response = new Response();
        List<ResponseItem> responseItems = new ArrayList<>();
        for(ItemAlbum albumItem : album.getResults()) {
            ResponseItem responseItem = new ResponseItem();
            responseItem.setArtistAuthor(albumItem.getArtistName());
            responseItem.setTitle(albumItem.getCollectionName());
            responseItem.setDescription(albumItem.getPrimaryGenreName());
            responseItems.add(responseItem);
        }
        return responseItems;
    }

    private List<ResponseItem> mapBook(Book book) {
        Response response = new Response();
        List<ResponseItem> responseItems = new ArrayList<>();
        for(Item bookItem : book.getItems()) {
            ResponseItem responseItem = new ResponseItem();
            responseItem.setArtistAuthor(bookItem.getVolumeInfo().getAuthors().toString());
            responseItem.setTitle(bookItem.getVolumeInfo().getTitle());
            responseItem.setDescription(bookItem.getVolumeInfo().getDescription());
            responseItems.add(responseItem);
        }
        return responseItems;
    }
}
