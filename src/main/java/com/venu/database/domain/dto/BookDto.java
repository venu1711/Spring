package com.venu.database.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private String isbn;

    private String title;

    private AuthorDto author; // Assuming AuthorDto is another DTO class representing the author

}
