package com.task.booknest.domains.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name="books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private String summary;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date publicationYear;
    private String coverImage;

    //La tabla va en el lado propetario quien ara las relaciones
    @ManyToMany
    @JoinTable(name="book_author",
            joinColumns = @JoinColumn(name = "book_ir"),
            inverseJoinColumns = @JoinColumn(name="author_id")
    )
    private List<Author> authors;

    @ManyToOne(fetch = FetchType.EAGER)
    private Genre genre;
}
