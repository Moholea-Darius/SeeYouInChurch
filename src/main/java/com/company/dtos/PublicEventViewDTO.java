package com.company.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicEventViewDTO {

    private int id;
    private String name;
    private String startDate;
    private String endDate;
    private String[] groups;
    private int roomId;

}
