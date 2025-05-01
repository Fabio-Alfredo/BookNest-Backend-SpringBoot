package com.task.booknest.domains.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="roles")
public class Role {

    @Id
    private String id;
    private String value;
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private User user;
}
