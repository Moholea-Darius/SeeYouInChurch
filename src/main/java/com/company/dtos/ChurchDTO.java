package com.company.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChurchDTO {

    private int id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;

}