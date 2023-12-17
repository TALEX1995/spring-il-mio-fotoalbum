package org.java.spring.auth.db.repository;

import org.java.spring.auth.db.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	User findByUsername(String username);
}
