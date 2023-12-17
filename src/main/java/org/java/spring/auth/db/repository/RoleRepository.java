package org.java.spring.auth.db.repository;

import org.java.spring.auth.db.pojo.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

}
