package com.search.search.service;

import com.search.search.mapper.BookMapper;
import com.search.search.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.apache.http.protocol.HTTP.USER_AGENT;

@Service
public class BookService {
    @Autowired
    BookMapper bookMapper;

    public BookService() {
    }

    public BookService(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    public ResponseEntity<Book> googleBooksAPI(String query, Integer limit) throws IOException {
        String url = "https://www.googleapis.com/books/v1/volumes?q="+ query +"&maxResults=" + limit.toString();
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        Book book = bookMapper.mapBook(response.toString());
        HttpStatus resolve = HttpStatus.resolve(con.getResponseCode());
        return new ResponseEntity<Book>(book, resolve);
    }
}
