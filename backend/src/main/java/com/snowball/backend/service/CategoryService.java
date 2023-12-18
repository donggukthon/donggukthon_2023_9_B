package com.snowball.backend.service;

import com.snowball.backend.entity.Category;
import com.snowball.backend.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    // 카테고리 id로 눈사람을 찾아 반환
    public Category getCategoryByCategoryId(Long categoryId) {
        return categoryRepository.findCategoryByCategoryId(categoryId);
    }
}
