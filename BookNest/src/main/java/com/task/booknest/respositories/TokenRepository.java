package com.task.booknest.respositories;

import com.task.booknest.domains.models.Token;
import com.task.booknest.domains.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TokenRepository extends JpaRepository<Token, UUID> {
    List<Token>findByUserAndActive(User user, Boolean active);
}
