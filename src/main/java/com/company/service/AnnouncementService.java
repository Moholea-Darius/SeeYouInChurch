package com.company.service;

import com.company.dtos.AnnouncementDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class AnnouncementService {

    private final String baseUrl = "http://localhost:8080/announcement";
    private final WebClient client = WebClient.create(baseUrl);

    public List<AnnouncementDTO> findAll() {
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

        return responseSpec.bodyToFlux(AnnouncementDTO.class).collectList().block();
    }

    public AnnouncementDTO findById(int id) {
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

        return responseSpec.bodyToMono(AnnouncementDTO.class).block();
    }

    public List<AnnouncementDTO> getLastThreeAnnouncements() {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.GET);

        WebClient.RequestBodySpec bodySpec = uriSpec.uri("/getLastThreeAnnouncements");

        WebClient.ResponseSpec responseSpec = bodySpec
                .bodyValue("data")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
                .acceptCharset(StandardCharsets.UTF_8)
                .ifNoneMatch("*")
                .ifModifiedSince(ZonedDateTime.now())
                .retrieve();

        return responseSpec.bodyToFlux(AnnouncementDTO.class).collectList().block();
    }

    public AnnouncementDTO add(AnnouncementDTO announcementDTO) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.POST);

        WebClient.RequestBodySpec bodySpec = uriSpec.uri("/add");

        WebClient.ResponseSpec responseSpec = bodySpec
                .bodyValue(announcementDTO)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
                .acceptCharset(StandardCharsets.UTF_8)
                .ifNoneMatch("*")
                .ifModifiedSince(ZonedDateTime.now())
                .retrieve();

        return responseSpec.bodyToMono(AnnouncementDTO.class).block();
    }

}
