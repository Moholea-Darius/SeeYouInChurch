package com.company.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementDTO implements Serializable {

    private int id;
    private String sender;
    private String message;
    private String type;
    private String sendDate;

}
