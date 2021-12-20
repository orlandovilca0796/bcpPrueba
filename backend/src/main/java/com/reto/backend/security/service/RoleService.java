package com.reto.backend.security.service;

import com.reto.backend.security.entity.Role;
import com.reto.backend.security.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public Optional<Role> findyByRoleName(String roleName){
        return this.roleRepository.findByRoleName(roleName);
    }
}
