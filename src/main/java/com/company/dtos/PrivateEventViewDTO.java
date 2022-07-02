package com.company.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrivateEventViewDTO {

    private int id;
    private String name;
    private String startDate;
    private String endDate;
    private String location;
    private String[] groups;
    private String details;

}
