package com.task.booknest.services.contract;


import com.task.booknest.domains.models.Role;

public interface RoleService {
    Role findById(String id);
}
