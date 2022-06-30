package com.company.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDTO {

    private int id;
    private String sender;
    private String title;
    private String message;
    private String sendDate;
    private String readDate;
    private String status;

}
