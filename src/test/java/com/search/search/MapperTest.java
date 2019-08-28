package com.search.search;

import com.search.search.mapper.AlbumMapper;
import com.search.search.mapper.BookMapper;
import com.search.search.mapper.ResponseMapper;
import com.search.search.model.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MapperTest {
    private AlbumMapper albumMapper;
    private BookMapper bookMapper;
    private ResponseMapper responseMapper;
    private Album expectedAlbum;
    private Book expectedBook;
    private List<Item> itemBookList;

    private String correctBookInput = "{\n" +
            " \"kind\": \"books#volumes\",\n" +
            " \"totalItems\": 473,\n" +
            " \"items\": [\n" +
            "  {\n" +
            "   \"kind\": \"books#volume\",\n" +
            "   \"id\": \"vRagDgAAQBAJ\",\n" +
            "   \"etag\": \"7W47UhG3Eh4\",\n" +
            "   \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/vRagDgAAQBAJ\",\n" +
            "   \"volumeInfo\": {\n" +
            "    \"title\": \"Onderweg\",\n" +
            "    \"authors\": [\n" +
            "     \"João Ricardo Pedro\"\n" +
            "    ],\n" +
            "    \"publisher\": \"Signatuur\",\n" +
            "    \"publishedDate\": \"2017-07-04\",\n" +
            "    \"description\": \"Na een treinramp in Noord-Portugal ontvangt Marta’s moeder een bericht: de rugzak van haar dochter, studente aan de kunstacademie, is tussen de brokstukken gevonden. Haar stoffelijk overschot is echter onvindbaar. Marta’s jongere broer João, een gevoelige jongen die een diepe bewondering én vrees koestert voor zijn sterke gewelddadige zus, blijft met veel vragen achter. Aan de hand van haar tekeningen en zijn herinneringen probeert hij de waarheid rond zijn zus te achterhalen. Een reeks bonte figuren trekt voorbij, terwijl hij zelf volledig ontbreekt in het leven dat ze schetst. João’s verhaal is een boeiende reconstructie van hun leven en roept vragen op over zijn eigen bestaansrecht. Een pijnlijk mooi verhaal over de dunne grens tussen realiteit en waanzin. Over Jouw gezicht zal het laatste zijn: ‘Pedro’s talent is onmiskenbaar.’ – NRC Handelsblad ‘Een boek dat je niet meteen loslaat als je het uit hebt. Pedro toont zoveel durf, originaliteit en talent voor sterke, beeldende scènes dat je razend benieuwd bent naar het vervolg van zijn schrijverschap.’ – Trouw ‘Overrompelend debuut over een veranderend Portugal.’ – de Volkskrant\",\n" +
            "    \"industryIdentifiers\": [\n" +
            "     {\n" +
            "      \"type\": \"ISBN_13\",\n" +
            "      \"identifier\": \"9789044976304\"\n" +
            "     },\n" +
            "     {\n" +
            "      \"type\": \"ISBN_10\",\n" +
            "      \"identifier\": \"9044976303\"\n" +
            "     }\n" +
            "    ],\n" +
            "    \"readingModes\": {\n" +
            "     \"text\": true,\n" +
            "     \"image\": false\n" +
            "    },\n" +
            "    \"printType\": \"BOOK\",\n" +
            "    \"categories\": [\n" +
            "     \"Fiction\"\n" +
            "    ],\n" +
            "    \"maturityRating\": \"NOT_MATURE\",\n" +
            "    \"allowAnonLogging\": false,\n" +
            "    \"contentVersion\": \"1.3.3.0.preview.2\",\n" +
            "    \"panelizationSummary\": {\n" +
            "     \"containsEpubBubbles\": false,\n" +
            "     \"containsImageBubbles\": false\n" +
            "    },\n" +
            "    \"imageLinks\": {\n" +
            "     \"smallThumbnail\": \"http://books.google.com/books/content?id=vRagDgAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
            "     \"thumbnail\": \"http://books.google.com/books/content?id=vRagDgAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
            "    },\n" +
            "    \"language\": \"nl\",\n" +
            "    \"previewLink\": \"http://books.google.nl/books?id=vRagDgAAQBAJ&printsec=frontcover&dq=joao&hl=&cd=1&source=gbs_api\",\n" +
            "    \"infoLink\": \"https://play.google.com/store/books/details?id=vRagDgAAQBAJ&source=gbs_api\",\n" +
            "    \"canonicalVolumeLink\": \"https://play.google.com/store/books/details?id=vRagDgAAQBAJ\"\n" +
            "   },\n" +
            "   \"saleInfo\": {\n" +
            "    \"country\": \"NL\",\n" +
            "    \"saleability\": \"FOR_SALE\",\n" +
            "    \"isEbook\": true,\n" +
            "    \"listPrice\": {\n" +
            "     \"amount\": 11.99,\n" +
            "     \"currencyCode\": \"EUR\"\n" +
            "    },\n" +
            "    \"retailPrice\": {\n" +
            "     \"amount\": 11.99,\n" +
            "     \"currencyCode\": \"EUR\"\n" +
            "    },\n" +
            "    \"buyLink\": \"https://play.google.com/store/books/details?id=vRagDgAAQBAJ&rdid=book-vRagDgAAQBAJ&rdot=1&source=gbs_api\",\n" +
            "    \"offers\": [\n" +
            "     {\n" +
            "      \"finskyOfferType\": 1,\n" +
            "      \"listPrice\": {\n" +
            "       \"amountInMicros\": 1.199E7,\n" +
            "       \"currencyCode\": \"EUR\"\n" +
            "      },\n" +
            "      \"retailPrice\": {\n" +
            "       \"amountInMicros\": 1.199E7,\n" +
            "       \"currencyCode\": \"EUR\"\n" +
            "      }\n" +
            "     }\n" +
            "    ]\n" +
            "   },\n" +
            "   \"accessInfo\": {\n" +
            "    \"country\": \"NL\",\n" +
            "    \"viewability\": \"PARTIAL\",\n" +
            "    \"embeddable\": true,\n" +
            "    \"publicDomain\": false,\n" +
            "    \"textToSpeechPermission\": \"ALLOWED\",\n" +
            "    \"epub\": {\n" +
            "     \"isAvailable\": true,\n" +
            "     \"acsTokenLink\": \"http://books.google.nl/books/download/Onderweg-sample-epub.acsm?id=vRagDgAAQBAJ&format=epub&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"\n" +
            "    },\n" +
            "    \"pdf\": {\n" +
            "     \"isAvailable\": false\n" +
            "    },\n" +
            "    \"webReaderLink\": \"http://play.google.com/books/reader?id=vRagDgAAQBAJ&hl=&printsec=frontcover&source=gbs_api\",\n" +
            "    \"accessViewStatus\": \"SAMPLE\",\n" +
            "    \"quoteSharingAllowed\": false\n" +
            "   },\n" +
            "   \"searchInfo\": {\n" +
            "    \"textSnippet\": \"Pedro toont zoveel durf, originaliteit en talent voor sterke, beeldende scènes dat je razend benieuwd bent naar het vervolg van zijn schrijverschap.’ – Trouw ‘Overrompelend debuut over een veranderend Portugal.’ – de Volkskrant\"\n" +
            "   }\n" +
            "  }\n" +
            " ]\n" +
            "}";
    private String correctAlbumInput =
            "{\n" +
                    " \"resultCount\":1,\n" +
                    " \"results\": [\n" +
                    "{\"wrapperType\":\"collection\", \"collectionType\":\"Album\", \"artistId\":1470489774, \"collectionId\":1422655717, \"amgArtistId\":6568, \"artistName\":\"Stan Getz & João Gilberto\", \"collectionName\":\"Getz/Gilberto\", \"collectionCensoredName\":\"Getz/Gilberto\", \"artistViewUrl\":\"https://music.apple.com/us/artist/stan-getz/1470489774?uo=4\", \"collectionViewUrl\":\"https://music.apple.com/us/album/getz-gilberto/1422655717?uo=4\", \"artworkUrl60\":\"https://is1-ssl.mzstatic.com/image/thumb/Music118/v4/13/bf/a4/13bfa405-d165-69b8-58ff-26e7b54994a1/source/60x60bb.jpg\", \"artworkUrl100\":\"https://is1-ssl.mzstatic.com/image/thumb/Music118/v4/13/bf/a4/13bfa405-d165-69b8-58ff-26e7b54994a1/source/100x100bb.jpg\", \"collectionPrice\":5.99, \"collectionExplicitness\":\"notExplicit\", \"trackCount\":8, \"copyright\":\"℗ 2011 The Verve Music Group, a Division of UMG Recordings, Inc.\", \"country\":\"USA\", \"currency\":\"USD\", \"releaseDate\":\"2011-01-01T08:00:00Z\", \"primaryGenreName\":\"Jazz\"}]\n" +
                    "}\n" +
                    "\n" +
                    "\n";
    private String wrongJson = "{";


    @Before
    public void setup() {
        albumMapper = new AlbumMapper();
        bookMapper = new BookMapper();
        responseMapper = new ResponseMapper();
        expectedAlbum = new Album();
        List<ItemAlbum> itemAlbumList = new ArrayList<ItemAlbum>();
        itemAlbumList.add(new ItemAlbum("Stan Getz & João Gilberto", "Getz/Gilberto", "Jazz"));
        expectedAlbum.setResults(itemAlbumList);
        expectedBook = new Book();
        itemBookList = new ArrayList<Item>();
        List<String> authors = new ArrayList<>();
        authors.add("João Ricardo Pedro");
        VolumeInfo volumeInfo = new VolumeInfo("Onderweg", authors, "Na een treinramp in Noord-Portugal ontvangt Marta’s moeder een bericht: de rugzak van haar dochter, studente aan de kunstacademie, is tussen de brokstukken gevonden. Haar stoffelijk overschot is echter onvindbaar. Marta’s jongere broer João, een gevoelige jongen die een diepe bewondering én vrees koestert voor zijn sterke gewelddadige zus, blijft met veel vragen achter. Aan de hand van haar tekeningen en zijn herinneringen probeert hij de waarheid rond zijn zus te achterhalen. Een reeks bonte figuren trekt voorbij, terwijl hij zelf volledig ontbreekt in het leven dat ze schetst. João’s verhaal is een boeiende reconstructie van hun leven en roept vragen op over zijn eigen bestaansrecht. Een pijnlijk mooi verhaal over de dunne grens tussen realiteit en waanzin. Over Jouw gezicht zal het laatste zijn: ‘Pedro’s talent is onmiskenbaar.’ – NRC Handelsblad ‘Een boek dat je niet meteen loslaat als je het uit hebt. Pedro toont zoveel durf, originaliteit en talent voor sterke, beeldende scènes dat je razend benieuwd bent naar het vervolg van zijn schrijverschap.’ – Trouw ‘Overrompelend debuut over een veranderend Portugal.’ – de Volkskrant\",\n");
        itemBookList.add(new Item(volumeInfo));
        expectedBook.setItems(itemBookList);
    }

    @Test
    public void albumMapperTestWithCorrectInputTest() throws IOException {
        Album album = albumMapper.mapAlbum(correctAlbumInput);
        assertEquals(expectedAlbum.getResults().get(0).getArtistName(), album.getResults().get(0).getArtistName());
        assertEquals(expectedAlbum.getResults().get(0).getCollectionName(), album.getResults().get(0).getCollectionName());
        assertEquals(expectedAlbum.getResults().get(0).getPrimaryGenreName(), album.getResults().get(0).getPrimaryGenreName());
    }

    @Test(expected = IOException.class)
    public void albumMapperTestWithWrongInputTest() throws IOException {
        albumMapper.mapAlbum(wrongJson);
    }

    @Test
    public void bookMapperTestWithCorrectInputTest() throws IOException {
        Book book =  bookMapper.mapBook(correctAlbumInput);
        book.setItems(itemBookList);
        assertEquals(expectedBook.getItems().get(0).getVolumeInfo().getAuthors(), book.getItems().get(0).getVolumeInfo().getAuthors());
        assertEquals(expectedBook.getItems().get(0).getVolumeInfo().getTitle(), book.getItems().get(0).getVolumeInfo().getTitle());
        assertEquals(expectedBook.getItems().get(0).getVolumeInfo().getDescription(), book.getItems().get(0).getVolumeInfo().getDescription());
    }

    @Test(expected = IOException.class)
    public void bookMapperTestWithWrongInputTest() throws IOException {
        bookMapper.mapBook(wrongJson);
    }

    @Test
    public void mapResponseTest() {
        Response expectedResponse = new Response();
        List<ResponseItem> responseItems = new ArrayList<>();
        responseItems.add(new ResponseItem("Stan Getz & João Gilberto", "Getz/Gilberto", "Jazz"));
        responseItems.add(new ResponseItem("João Ricardo Pedro", "Onderweg",  "Na een treinramp in Noord-Portugal ontvangt Marta’s moeder een bericht: de rugzak van haar dochter, studente aan de kunstacademie, is tussen de brokstukken gevonden. Haar stoffelijk overschot is echter onvindbaar. Marta’s jongere broer João, een gevoelige jongen die een diepe bewondering én vrees koestert voor zijn sterke gewelddadige zus, blijft met veel vragen achter. Aan de hand van haar tekeningen en zijn herinneringen probeert hij de waarheid rond zijn zus te achterhalen. Een reeks bonte figuren trekt voorbij, terwijl hij zelf volledig ontbreekt in het leven dat ze schetst. João’s verhaal is een boeiende reconstructie van hun leven en roept vragen op over zijn eigen bestaansrecht. Een pijnlijk mooi verhaal over de dunne grens tussen realiteit en waanzin. Over Jouw gezicht zal het laatste zijn: ‘Pedro’s talent is onmiskenbaar.’ – NRC Handelsblad ‘Een boek dat je niet meteen loslaat als je het uit hebt. Pedro toont zoveel durf, originaliteit en talent voor sterke, beeldende scènes dat je razend benieuwd bent naar het vervolg van zijn schrijverschap.’ – Trouw ‘Overrompelend debuut over een veranderend Portugal.’ – de Volkskrant\",\n"));
        expectedResponse.setResponseItemList(responseItems);
        Response response = responseMapper.mapResponse(expectedAlbum, expectedBook);
        assertEquals(expectedResponse.getResponseItemList().get(0).getTitle(), response.getResponseItemList().get(0).getTitle());
        assertEquals(expectedResponse.getResponseItemList().get(0).getTitle(), response.getResponseItemList().get(0).getTitle());
        assertEquals(expectedResponse.getResponseItemList().get(0).getTitle(), response.getResponseItemList().get(0).getTitle());

        assertEquals(expectedResponse.getResponseItemList().get(1).getTitle(), response.getResponseItemList().get(1).getTitle());
        assertEquals(expectedResponse.getResponseItemList().get(1).getTitle(), response.getResponseItemList().get(1).getTitle());
        assertEquals(expectedResponse.getResponseItemList().get(1).getTitle(), response.getResponseItemList().get(1).getTitle());

    }
}
