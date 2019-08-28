package com.search.search.service;

import com.search.search.mapper.AlbumMapper;
import com.search.search.model.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import static org.apache.http.protocol.HTTP.USER_AGENT;

@Service
public class AlbumService {
    @Autowired
    AlbumMapper albumMapper;

    public AlbumService() {
    }

    public AlbumService(AlbumMapper albumMapper) {
        this.albumMapper = albumMapper;
    }

    public ResponseEntity<Album> appleAlbumAPI(String query, Integer limit) throws IOException {
        String url = "https://itunes.apple.com/search?term=" + query + "&entity=album&limit=" + limit.toString();
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

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
        Album album = albumMapper.mapAlbum(response.toString());
        HttpStatus resolve = HttpStatus.resolve(con.getResponseCode());
        return new ResponseEntity<Album>(album, resolve);
    }
}
