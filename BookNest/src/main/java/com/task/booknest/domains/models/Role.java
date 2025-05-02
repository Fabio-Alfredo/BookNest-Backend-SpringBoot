package com.task.booknest.domains.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name="roles")
public class Role {

    @Id
    private String id;
    private String value;
    private String description;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private List<User> user;
}
