package com.bytes.and.dragons.fantasyauction.repository;

import com.bytes.and.dragons.fantasyauction.model.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleName(String name);
}
