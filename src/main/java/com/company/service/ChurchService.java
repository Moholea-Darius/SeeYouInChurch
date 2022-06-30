package com.company.service;

import com.company.dtos.ChurchDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;

@Service
public class ChurchService {

    private final String baseUrl = "http://localhost:8080/church";
    private final WebClient client = WebClient.create(baseUrl);

    public ChurchDTO getChurch() {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.GET);

        WebClient.RequestBodySpec bodySpec = uriSpec.uri("/getChurch");

        WebClient.ResponseSpec responseSpec = bodySpec
                .bodyValue("data")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
                .acceptCharset(StandardCharsets.UTF_8)
                .ifNoneMatch("*")
                .ifModifiedSince(ZonedDateTime.now())
                .retrieve();

        return responseSpec.bodyToMono(ChurchDTO.class).block();
    }

    public ChurchDTO updateEmail(String email) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.PUT);

        WebClient.RequestBodySpec bodySpec = uriSpec.uri("/updateEmail?email=" + email);

        WebClient.ResponseSpec responseSpec = bodySpec
                .bodyValue("data")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
                .acceptCharset(StandardCharsets.UTF_8)
                .ifNoneMatch("*")
                .ifModifiedSince(ZonedDateTime.now())
                .retrieve();

        return responseSpec.bodyToMono(ChurchDTO.class).block();
    }

    public ChurchDTO updatePhoneNumber(String phoneNumber) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.PUT);

        WebClient.RequestBodySpec bodySpec = uriSpec.uri("/updatePhoneNumber?phoneNumber=" + phoneNumber);

        WebClient.ResponseSpec responseSpec = bodySpec
                .bodyValue("data")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
                .acceptCharset(StandardCharsets.UTF_8)
                .ifNoneMatch("*")
                .ifModifiedSince(ZonedDateTime.now())
                .retrieve();

        return responseSpec.bodyToMono(ChurchDTO.class).block();
    }


}
