package com.search.search.controller;

import com.search.search.model.Response;
import com.search.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/books")
public class SearchController {

    @Autowired
    SearchService searchService;

    @GetMapping(path = "/search/{query}")
    public ResponseEntity<Response> search(@PathVariable("query") String query) throws IOException {
        return searchService.search(query);
    }
}
