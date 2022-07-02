package com.company.service;

import com.company.dtos.GroupDTO;
import com.company.dtos.UserDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class GroupService {

    private final String baseUrl = "http://localhost:8080/groups";
    private final WebClient client = WebClient.create(baseUrl);

    public List<GroupDTO> findAll() {
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

        return responseSpec.bodyToFlux(GroupDTO.class).collectList().block();
    }

    public GroupDTO findById(int id) {
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

        return responseSpec.bodyToMono(GroupDTO.class).block();
    }

    public List<GroupDTO> findByUserId(int userId) {
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

        return responseSpec.bodyToFlux(GroupDTO.class).collectList().block();
    }

    //hack
    public List<UserDTO> findUsersByGroupId(List<UserDTO> userDTOS, int groupId) {
        List<UserDTO> finalUserDtoList = new ArrayList<>();
        for (UserDTO userDTO : userDTOS) {
            List<GroupDTO> groupDTOS = findByUserId(userDTO.getId());
            if (!groupDTOS.isEmpty()) {
                for (GroupDTO groupDTO : groupDTOS) {
                    if (groupDTO.getId() == groupId) {
                        finalUserDtoList.add(userDTO);
                    }
                }
            }
        }
        return finalUserDtoList;
    }

    //hack
    public String getUrlForUpdate(List<UserDTO> userDTOS, int groupId) {
        String url = "?";
        for (UserDTO userDTO : findUsersByGroupId(userDTOS, groupId)) {
            url += "userId=" + userDTO.getId() + "&";
        }
        System.out.println(url);
        return url;
    }

//foloseste hack
    public GroupDTO update(GroupDTO groupDTO, List<UserDTO> userDTOS) {
        groupDTO.setName(findById(groupDTO.getId()).getName());
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.PUT);

        WebClient.RequestBodySpec bodySpec = uriSpec.uri("/update" + getUrlForUpdate(userDTOS, groupDTO.getId()));

        WebClient.ResponseSpec responseSpec = bodySpec
                .bodyValue(groupDTO)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
                .acceptCharset(StandardCharsets.UTF_8)
                .ifNoneMatch("*")
                .ifModifiedSince(ZonedDateTime.now())
                .retrieve();

        return responseSpec.bodyToMono(GroupDTO.class).block();
    }

    public GroupDTO add(int userId, GroupDTO groupDTO) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.POST);

        WebClient.RequestBodySpec bodySpec = uriSpec.uri("/add?userId=" + userId);

        WebClient.ResponseSpec responseSpec = bodySpec
                .bodyValue(groupDTO)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
                .acceptCharset(StandardCharsets.UTF_8)
                .ifNoneMatch("*")
                .ifModifiedSince(ZonedDateTime.now())
                .retrieve();

        return responseSpec.bodyToMono(GroupDTO.class).block();
    }

//hack
    public List<GroupDTO> findGroups(int userId) {
        List<GroupDTO> groupDTOS = new ArrayList<>();
        for (GroupDTO dto : findAll()) {
            boolean exist = false;
            for (GroupDTO dto2 : findByUserId(userId)) {
                if (dto.getId() == dto2.getId()) {
                    exist = true;
                    break;
                }
            }
            if (!exist) {
                groupDTOS.add(dto);
            }
        }
        return groupDTOS;
    }


}
