package com.company.controller;

import com.company.dtos.ChurchDTO;
import com.company.model.Church;
import com.company.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;


    @Controller
    @RequestMapping("/church")
    public class ChurchController {

        private static final String CHURCH_BASE_URL = "http://localhost:8080/church/";
        private final WebClient backendClient = WebClient.create();

        @PostMapping()
        public void church(@RequestBody ChurchDTO churchDTO) {
            Church church = backendClient
                    .post().uri(CHURCH_BASE_URL)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(Church.class)
                    .block();
        }

        @GetMapping()
        public void church() {
            Church church = backendClient
                    .get().uri(CHURCH_BASE_URL)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(Church.class)
                    .block();
        }

        @PutMapping("/updateEmail")
        public void updateEmail( @RequestParam("email") String email) {
            Church church = backendClient
                    .put().uri(CHURCH_BASE_URL + "updateEmail/" + email)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(Church.class)
                    .block();
        }

        @PutMapping("/updatePhoneNumber")
        public void updatePhoneNumber(@RequestParam("phoneNumber") String phoneNumber) {
            Church church = backendClient
                    .put().uri(CHURCH_BASE_URL + "phoneNumber/" + phoneNumber)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(Church.class)
                    .block();
        }



}
