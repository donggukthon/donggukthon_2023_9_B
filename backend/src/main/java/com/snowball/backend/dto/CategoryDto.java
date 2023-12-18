package com.snowball.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class CategoryDto {
    @Getter
    @AllArgsConstructor
    @Builder
    public static class Data {
        private Long category_id;
        private String category_name;
        private Long fit_category_id;
        private String fit_category_name;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private Data data;
        private int return_code;
        private String return_message;
    }
}
