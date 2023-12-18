package com.snowball.backend.repository;

import com.snowball.backend.entity.Category;
import com.snowball.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findCategoryByCategoryId(Long id);
}

