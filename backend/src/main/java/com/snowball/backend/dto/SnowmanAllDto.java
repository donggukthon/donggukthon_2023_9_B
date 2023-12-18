package com.snowball.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class SnowmanAllDto {
    @Getter
    @AllArgsConstructor
    @Builder
    public static class Data {
        private List<SnowmanDto> all_snowman_list;
    }
}
