package com.search.search;

import com.search.search.mapper.AlbumMapper;
import com.search.search.mapper.BookMapper;
import com.search.search.model.Album;
import com.search.search.model.Book;
import com.search.search.model.Item;
import com.search.search.model.ItemAlbum;
import com.search.search.service.AlbumService;
import com.search.search.service.BookService;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class BookServiceTest {
    @Test
    public void bookServiceTest() throws IOException {
        BookMapper mockedBookMapper = Mockito.mock(BookMapper.class);
        Book expectedBook= new Book();
        List<Item> itemBookList = new ArrayList<Item>();
        itemBookList.add(new Item());
        when(mockedBookMapper.mapBook(anyString())).thenReturn(expectedBook);

        BookService bookService = new BookService(mockedBookMapper);
        assertNotNull(bookService.googleBooksAPI("joao", 5));
    }
}
