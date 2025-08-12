package com.venu.database.domain;


import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity // This annotation marks this class as a JPA entity
@Table(name = "authors")
public class Author {
//  @Data annotation creates Getters and Setters for these private objects
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_id_seq") // This annotation marks this field as the primary key and generates its value automatically
    private Long id; //Use Long instead long which helps to store null values

    private String name;

    private Integer age;
}
