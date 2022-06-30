package com.company.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {

    private int id;
    private int number;
    private int maxCapacity;
    private String status;

}