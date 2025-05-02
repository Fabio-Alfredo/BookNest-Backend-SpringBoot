package com.task.booknest.services.impl;

import com.task.booknest.domains.models.Role;
import com.task.booknest.respositories.RoleRepository;
import com.task.booknest.services.contract.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findById(String id) {
        try{
            Role role = roleRepository.findById(id).orElse(null);
            if(role ==null){
                throw new RuntimeException("Role not exists");
            }
            return role;
        }catch (Exception e){
            throw  new RuntimeException("Error while find role: " +e.getMessage());
        }
    }
}
