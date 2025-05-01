package com.task.booknest.domains.models;

import jakarta.persistence.*;
import lombok.Data;

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
    private Date publicationYear;
    private String coverImage;

    @ManyToMany(mappedBy = "books")
    private List<Author> authors;

    @ManyToOne(fetch = FetchType.EAGER)
    private Genre genre;
}
