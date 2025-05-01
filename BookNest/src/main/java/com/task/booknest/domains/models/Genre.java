package com.task.booknest.domains.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name="genres")
public class Genre {

    @Id
    private String id;
    private String genre;

    @OneToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Book>books;
}
