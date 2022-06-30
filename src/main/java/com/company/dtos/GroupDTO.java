package com.company.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupDTO {

    private int id;
    @NotBlank
    private String name;
    private String description;

}
