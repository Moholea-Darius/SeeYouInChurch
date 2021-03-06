package com.company.service;

import com.company.dtos.RoomDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class RoomService {

    private final String baseUrl = "http://localhost:8080/room";
    private final WebClient client = WebClient.create(baseUrl);

    public List<RoomDTO> findAll() {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.GET);

        WebClient.RequestBodySpec bodySpec = uriSpec.uri("/findAll");

        WebClient.ResponseSpec responseSpec = bodySpec
                .bodyValue("data")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
                .acceptCharset(StandardCharsets.UTF_8)
                .ifNoneMatch("*")
                .ifModifiedSince(ZonedDateTime.now())
                .retrieve();

        return responseSpec.bodyToFlux(RoomDTO.class).collectList().block();
    }

    public RoomDTO findById(int id) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.GET);

        WebClient.RequestBodySpec bodySpec = uriSpec.uri("/findById?id=" + id);

        WebClient.ResponseSpec responseSpec = bodySpec
                .bodyValue("data")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
                .acceptCharset(StandardCharsets.UTF_8)
                .ifNoneMatch("*")
                .ifModifiedSince(ZonedDateTime.now())
                .retrieve();

        return responseSpec.bodyToMono(RoomDTO.class).block();
    }

    public RoomDTO findByNumber(int number) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.GET);

        WebClient.RequestBodySpec bodySpec = uriSpec.uri("/findByNumber?number=" + number);

        WebClient.ResponseSpec responseSpec = bodySpec
                .bodyValue("data")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
                .acceptCharset(StandardCharsets.UTF_8)
                .ifNoneMatch("*")
                .ifModifiedSince(ZonedDateTime.now())
                .retrieve();

        return responseSpec.bodyToMono(RoomDTO.class).block();
    }

    public RoomDTO updateRoomStatusByRoomNo(int number, String status) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.PUT);

        WebClient.RequestBodySpec bodySpec = uriSpec.uri("/updateRoomStatusByRoomNo?roomNo=" + number + "&status=" + status);

        WebClient.ResponseSpec responseSpec = bodySpec
                .bodyValue("data")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
                .acceptCharset(StandardCharsets.UTF_8)
                .ifNoneMatch("*")
                .ifModifiedSince(ZonedDateTime.now())
                .retrieve();

        return responseSpec.bodyToMono(RoomDTO.class).block();
    }

}
