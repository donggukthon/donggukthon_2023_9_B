package com.snowball.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class MyInfoDto {
    @Getter
    @AllArgsConstructor
    @Builder
    public static class Data {
        private Long snowman_id;
        private Long user_id;
        private Long category_id;
        private String snowman_name;
        private String register_date;
        private String gender;
        private String introduce;
        private String sns_kind;
        private String sns_id;
        private Boolean is_expose;
        private Long warm_snow;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private MyInfoDto.Data data;
        private int return_code;
        private String return_message;
    }
}
