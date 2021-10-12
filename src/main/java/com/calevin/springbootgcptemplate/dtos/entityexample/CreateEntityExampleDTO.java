package com.calevin.springbootgcptemplate.dtos.entityexample;

import lombok.Data;

@Data
public class CreateEntityExampleDTO {
    private String name;
    private String description;
    private Long categoryId;
}
