package com.company.service;

import com.company.dtos.NotificationDTO;
import com.company.dtos.constant.NotificationStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {

    private final String baseUrl = "http://localhost:8080/notification";
    private final WebClient client = WebClient.create(baseUrl);

    public List<NotificationDTO> findAll() {
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

        return responseSpec.bodyToFlux(NotificationDTO.class).collectList().block();
    }

    public NotificationDTO findById(int id) {
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

        return responseSpec.bodyToMono(NotificationDTO.class).block();
    }

    public List<NotificationDTO> findByUserId(int userId) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.GET);

        WebClient.RequestBodySpec bodySpec = uriSpec.uri("/findByUserId?userId=" + userId);

        WebClient.ResponseSpec responseSpec = bodySpec
                .bodyValue("data")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
                .acceptCharset(StandardCharsets.UTF_8)
                .ifNoneMatch("*")
                .ifModifiedSince(ZonedDateTime.now())
                .retrieve();

        return responseSpec.bodyToFlux(NotificationDTO.class).collectList().block();
    }

    public NotificationDTO update(int messageId) {
        NotificationDTO notificationDTO = findById(messageId);
        notificationDTO.setReadDate(LocalDateTime.now().toString());
        notificationDTO.setStatus(NotificationStatus.READ.name());

        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.PUT);

        WebClient.RequestBodySpec bodySpec = uriSpec.uri("/update?messageId=" + messageId + "&readDate=" + notificationDTO.getReadDate());

        WebClient.ResponseSpec responseSpec = bodySpec
                .bodyValue("data")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
                .acceptCharset(StandardCharsets.UTF_8)
                .ifNoneMatch("*")
                .ifModifiedSince(ZonedDateTime.now())
                .retrieve();

        return responseSpec.bodyToMono(NotificationDTO.class).block();
    }

    public NotificationDTO add(int userId, NotificationDTO notificationDTO) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.POST);

        WebClient.RequestBodySpec bodySpec = uriSpec.uri("/save?userId=" + userId);

        WebClient.ResponseSpec responseSpec = bodySpec
                .bodyValue(notificationDTO)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
                .acceptCharset(StandardCharsets.UTF_8)
                .ifNoneMatch("*")
                .ifModifiedSince(ZonedDateTime.now())
                .retrieve();

        return responseSpec.bodyToMono(NotificationDTO.class).block();
    }

    public List<NotificationDTO> getUserUnreadNotifications(int userId) {
        List<NotificationDTO> notificationDTOS = new ArrayList<>();
        for (NotificationDTO dto : findByUserId(userId)) {
            if (dto.getStatus().equals(NotificationStatus.UNREAD.name())) {
                notificationDTOS.add(dto);
            }
        }
        return notificationDTOS;
    }

    public void updateMessages(List<NotificationDTO> userUnreadNotifications) {
        for (NotificationDTO notification : userUnreadNotifications) {
            update(notification.getId());
        }
    }
}
