package com.company.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementDTO {

    private int id;
    private String sender;
    private String message;
    private String type;
    private String sendDate;

}
