package com.company.controller;

import com.company.model.Room;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;


@Controller
@RequestMapping("/room")
public class RoomController {

    private static final String ROOMS_BASE_URL = "http://localhost:8080/room/";
    private final WebClient backendClient = WebClient.create();

    @GetMapping("/findAll")
    public void rooms() {
        Room[] rooms = backendClient
                .get().uri(ROOMS_BASE_URL + "findAll")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Room[].class)
                .block();
    }

    @GetMapping("/findById")
    public void room(@RequestParam("id") int id) {
        Room room = backendClient
                .get().uri(ROOMS_BASE_URL + "findById/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Room.class)
                .block();
    }

    @GetMapping("/findByNumber")
    public void roomByNumber(@RequestParam("number") int number) {
        Room room = backendClient
                .get().uri(ROOMS_BASE_URL + "findByNumber/" + number)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Room.class)
                .block();
    }

    @PutMapping("/updateRoomStatus")
    public void Put(@RequestParam("number") int number) {
        Room room = backendClient
                .put().uri(ROOMS_BASE_URL + "updateRoomStatus/" + number)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Room.class)
                .block();
    }
}
