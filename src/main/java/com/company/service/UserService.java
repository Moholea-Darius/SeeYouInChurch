package com.company.service;

import com.company.dtos.UserDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final String baseUrl = "http://localhost:8080/users";
    private final WebClient client = WebClient.create(baseUrl);

    public List<UserDTO> findAll() {
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

        return responseSpec.bodyToFlux(UserDTO.class).collectList().block();
    }

    public UserDTO findById(int id) {
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

        return responseSpec.bodyToMono(UserDTO.class).block();
    }

    public UserDTO findByEmail(String email) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.GET);

        WebClient.RequestBodySpec bodySpec = uriSpec.uri("/findByEmail?email=" + email);

        WebClient.ResponseSpec responseSpec = bodySpec
                .bodyValue("data")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
                .acceptCharset(StandardCharsets.UTF_8)
                .ifNoneMatch("*")
                .ifModifiedSince(ZonedDateTime.now())
                .retrieve();

        return responseSpec.bodyToMono(UserDTO.class).block();
    }

    public Object update(UserDTO userDTO) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.PUT);

        WebClient.RequestBodySpec bodySpec = uriSpec.uri("/update");

        WebClient.ResponseSpec responseSpec = bodySpec
                .bodyValue(userDTO)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
                .acceptCharset(StandardCharsets.UTF_8)
                .ifNoneMatch("*")
                .ifModifiedSince(ZonedDateTime.now())
                .retrieve();

        return responseSpec.bodyToMono(UserDTO.class).block();
    }

    public Object add(UserDTO userDTO) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.POST);

        WebClient.RequestBodySpec bodySpec = uriSpec.uri("/add");

        WebClient.ResponseSpec responseSpec = bodySpec
                .bodyValue(userDTO)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
                .acceptCharset(StandardCharsets.UTF_8)
                .ifNoneMatch("*")
                .ifModifiedSince(ZonedDateTime.now())
                .retrieve();

        return responseSpec.bodyToMono(UserDTO.class).block();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return findByEmail(email);
    }
}
