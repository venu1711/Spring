package com.venu.database.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "books")
public class Book {
    @Id
    private String isbn;

    private String title;

    @JoinColumn(name = "author_id")
    @ManyToOne(cascade = CascadeType.ALL) //When a book is saved, respective author is also saved
    private Author author;
    // The @ManyToOne annotation indicates that many books can be associated with one author.
}
