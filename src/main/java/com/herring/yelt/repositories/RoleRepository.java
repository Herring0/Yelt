package com.herring.yelt.repositories;

import com.herring.yelt.models.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findById(int id);
}
