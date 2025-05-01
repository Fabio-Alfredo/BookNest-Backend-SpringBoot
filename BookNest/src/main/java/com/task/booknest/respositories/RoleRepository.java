package com.task.booknest.respositories;

import com.task.booknest.domains.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}
