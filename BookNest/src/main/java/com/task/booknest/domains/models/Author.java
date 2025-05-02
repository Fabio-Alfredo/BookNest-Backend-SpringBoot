package com.task.booknest.domains.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name="authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String biography;
    private Date birthDate;
    private String photo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Book>books;
}
