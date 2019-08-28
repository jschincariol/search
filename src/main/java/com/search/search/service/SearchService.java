package com.search.search.service;

import com.search.search.mapper.ResponseMapper;
import com.search.search.model.Album;
import com.search.search.model.Book;
import com.search.search.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;

@Service
public class SearchService {
    @Autowired
    BookService bookService;
    @Autowired
    AlbumService albumService;
    @Autowired
    ResponseMapper responseMapper;
    int limit = 5;

    public SearchService() {
    }

    public SearchService(BookService bookService, AlbumService albumService, ResponseMapper responseMapper) {
        this.bookService = bookService;
        this.albumService = albumService;
        this.responseMapper = responseMapper;
    }

    public ResponseEntity<Response> search(String query) throws IOException {
        ResponseEntity<Book> bookResponse = bookService.googleBooksAPI(query, limit);
        ResponseEntity<Album> albumResponse = albumService.appleAlbumAPI(query, limit);
        Response response = responseMapper.mapResponse(albumResponse.getBody(), bookResponse.getBody());
        Collections.sort(response.getResponseItemList());
        if (!bookResponse.getStatusCode().is2xxSuccessful()) {
            return new ResponseEntity<Response>(response, bookResponse.getStatusCode());
        } else if(!albumResponse.getStatusCode().is2xxSuccessful()){
            return new ResponseEntity<Response>(response, albumResponse.getStatusCode());
        }
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }
}
