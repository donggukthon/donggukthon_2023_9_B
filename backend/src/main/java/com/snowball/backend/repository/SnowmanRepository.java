package com.snowball.backend.repository;

import com.snowball.backend.entity.Snowman;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SnowmanRepository extends JpaRepository<Snowman, Long> {
    Snowman findSnowmanBySnowmanId(Long snowmanId);
    Snowman findSnowmanByUserId(Long userId);
}
