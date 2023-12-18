package com.snowball.backend.repository;

import com.snowball.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByUserId(Long id);
    User findUserByProviderId(String providerId);
}
