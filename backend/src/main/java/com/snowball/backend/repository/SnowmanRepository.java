package com.snowball.backend.repository;

import com.snowball.backend.entity.Snowman;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SnowmanRepository extends JpaRepository<Snowman, Long> {
    Snowman findSnowmanBySnowmanId(Long snowmanId);
    Snowman findSnowmanByUserId(Long userId);

    List<Snowman> findAllByCategoryId(Long categoryId);

    List<Snowman> findAll();
}
