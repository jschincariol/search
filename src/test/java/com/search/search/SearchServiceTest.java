package com.search.search;

import com.search.search.mapper.ResponseMapper;
import com.search.search.model.Album;
import com.search.search.model.Book;
import com.search.search.model.Response;
import com.search.search.model.ResponseItem;
import com.search.search.service.AlbumService;
import com.search.search.service.BookService;
import com.search.search.service.SearchService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class SearchServiceTest {
    @Test
    public void searchServiceTest() throws IOException {
        BookService bookService = Mockito.mock(BookService.class);
        AlbumService albumService = Mockito.mock(AlbumService.class);
        ResponseMapper responseMapper = Mockito.mock(ResponseMapper.class);
        when(bookService.googleBooksAPI(anyString(), anyInt())).thenReturn(new ResponseEntity<Book>(new Book(), HttpStatus.OK));
        when( albumService.appleAlbumAPI(anyString(), anyInt())).thenReturn(new ResponseEntity<Album>(new Album(), HttpStatus.OK));
        Response expectedResponse = new Response();
        List<ResponseItem> responseItems = new ArrayList<>();
        responseItems.add(new ResponseItem("Stan Getz & João Gilberto", "Getz/Gilberto", "Jazz"));
        responseItems.add(new ResponseItem("João Ricardo Pedro", "Onderweg",  "Na een treinramp in Noord-Portugal ontvangt Marta’s moeder een bericht: de rugzak van haar dochter, studente aan de kunstacademie, is tussen de brokstukken gevonden. Haar stoffelijk overschot is echter onvindbaar. Marta’s jongere broer João, een gevoelige jongen die een diepe bewondering én vrees koestert voor zijn sterke gewelddadige zus, blijft met veel vragen achter. Aan de hand van haar tekeningen en zijn herinneringen probeert hij de waarheid rond zijn zus te achterhalen. Een reeks bonte figuren trekt voorbij, terwijl hij zelf volledig ontbreekt in het leven dat ze schetst. João’s verhaal is een boeiende reconstructie van hun leven en roept vragen op over zijn eigen bestaansrecht. Een pijnlijk mooi verhaal over de dunne grens tussen realiteit en waanzin. Over Jouw gezicht zal het laatste zijn: ‘Pedro’s talent is onmiskenbaar.’ – NRC Handelsblad ‘Een boek dat je niet meteen loslaat als je het uit hebt. Pedro toont zoveel durf, originaliteit en talent voor sterke, beeldende scènes dat je razend benieuwd bent naar het vervolg van zijn schrijverschap.’ – Trouw ‘Overrompelend debuut over een veranderend Portugal.’ – de Volkskrant\",\n"));
        expectedResponse.setResponseItemList(responseItems);

        when(responseMapper.mapResponse(any(), any())).thenReturn(expectedResponse);

        SearchService searchService = new SearchService(bookService, albumService, responseMapper);
        Assert.assertNotNull(searchService.search("joao"));
    }
}
