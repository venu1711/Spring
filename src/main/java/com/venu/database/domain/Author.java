package com.venu.database.domain;


import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Author {
//  @Data annotation creates Getters and Setters for these private objects
    private Long id; //Use Long instead long which helps to store null values

    private String name;

    private Integer age;
}
