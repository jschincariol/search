package com.search.search;

import com.search.search.mapper.AlbumMapper;
import com.search.search.model.Album;
import com.search.search.model.ItemAlbum;
import com.search.search.service.AlbumService;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class AlbumServiceTest {

    @Test
    public void albumServiceTest() throws IOException {
        AlbumMapper mockedAlbumMapper = Mockito.mock(AlbumMapper.class);
        Album expectedAlbum= new Album();
        List<ItemAlbum> itemAlbumList = new ArrayList<ItemAlbum>();
        itemAlbumList.add(new ItemAlbum("Stan Getz & João Gilberto", "Getz/Gilberto", "Jazz"));
        expectedAlbum.setResults(itemAlbumList);
        when(mockedAlbumMapper.mapAlbum(anyString())).thenReturn(expectedAlbum);

        AlbumService albumService = new AlbumService(mockedAlbumMapper);
        assertNotNull(albumService.appleAlbumAPI("joao", 5));
    }
}
