package com.snowball.backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Snowman {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "snowman_id")
    private Long snowmanId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Column(name = "snowman_name", nullable = false)
    private String snowmanName;

    @Column(name = "register_date", nullable = false)
    private String registerDate;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "introduce", nullable = false)
    private String introduce;

    @Column(name = "sns_kind")
    private String snsKind;

    @Column(name = "sns_id")
    private String snsId;

    @Column(name = "is_expose", nullable = false)
    private Boolean isExpose;
}
