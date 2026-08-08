package com.comeback.app.users.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.comeback.app.users.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
  boolean existsByEmail(String email);

  Optional<User> findByEmail(String email);
}
