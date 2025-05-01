package com.task.booknest.domains.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Table(name="tokens")
@Entity
@Data
@NoArgsConstructor
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String content;
    @Column(name="timestamp", updatable = false)
    private Date timestamp;
    private Boolean active;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_code")
    private User user;

    public Token(User user, String content){
        this.content = content;
        this.user = user;
        this.active=true;
        this.timestamp = Date.from(Instant.now());
    }

}
