package com.calevin.springbootgcptemplate.dtos.entityexample;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
public class GetEntityExampleDTO {
    private long id;
    private String name;
    private String categoryName;
}
