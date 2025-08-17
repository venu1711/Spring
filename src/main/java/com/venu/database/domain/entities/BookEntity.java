package com.venu.database.domain.entities;


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
public class BookEntity {
    @Id
    private String isbn;

    private String title;

    @JoinColumn(name = "author_id")
    @ManyToOne(cascade = CascadeType.ALL) //When a book is saved, respective authorEntity is also saved
    private AuthorEntity author;
    // The @ManyToOne annotation indicates that many books can be associated with one authorEntity.
}
