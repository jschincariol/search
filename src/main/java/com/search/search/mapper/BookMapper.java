package com.search.search.mapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.search.search.model.Book;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class BookMapper {
        public Book mapBook(String jsonString) throws IOException {
            try {
                final ObjectMapper mapper = new ObjectMapper();
                mapper.readTree(jsonString);
            } catch (IOException e) {
                throw new IOException("JSON not valid " + e);
            }
            return new ObjectMapper().readValue(jsonString, Book.class);
        }
}
