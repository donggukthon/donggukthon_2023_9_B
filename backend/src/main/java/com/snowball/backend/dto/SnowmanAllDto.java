package com.snowball.backend.dto;

import com.snowball.backend.entity.Snowman;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class SnowmanAllDto {
    @Getter
    @AllArgsConstructor
    @Builder
    public static class Data {
        private List<Snowman> all_snowman_list;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private SnowmanAllDto.Data data;
        private int return_code;
        private String return_message;
    }
}
