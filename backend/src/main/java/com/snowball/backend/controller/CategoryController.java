package com.snowball.backend.controller;

import com.snowball.backend.dto.CategoryDto;
import com.snowball.backend.entity.Category;
import com.snowball.backend.service.CategoryService;
import com.snowball.backend.util.TestResultUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test-result")
public class CategoryController {
    private final CategoryService categoryService;
    private final TestResultUtil testResultUtil;

    @GetMapping
    // 테스트 결과를 통해 카테고리 id를 반환하는 API
    public CategoryDto.Response getTestResult(
            @RequestParam(name = "result") String testResult) {
        // 테스트 결과 도출
        Long categoryId = testResultUtil.getCategoryIdByTestResult(testResult);
        Category category = categoryService.getCategoryByCategoryId(categoryId);
        Category fitCategory = categoryService.getCategoryByCategoryId(category.getFitCategoryId());

        return new CategoryDto.Response(
                new CategoryDto.Data(
                        category.getCategoryId(),
                        category.getCategoryName(),
                        fitCategory.getCategoryId(),
                        fitCategory.getCategoryName()),
                200,
                "success");
    }
}
