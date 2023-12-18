package com.snowball.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class SnowmanDto {
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
    }

    @Getter
    @Setter
    public static class Request {
        private Long category_id;
        private String snowman_name;
        private String gender;
        private String introduce;
        private String sns_kind;
        private String sns_id;
        private Boolean is_expose;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private Data data;
        private int return_code;
        private String return_message;
    }
}
