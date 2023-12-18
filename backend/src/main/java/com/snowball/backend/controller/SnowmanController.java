package com.snowball.backend.controller;

import com.snowball.backend.config.jwt.JwtProvider;
import com.snowball.backend.dto.SnowmanDto;
import com.snowball.backend.entity.Snowman;
import com.snowball.backend.service.SnowmanService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class SnowmanController {
    private final SnowmanService snowmanService;
    private final JwtProvider jwtProvider;

    @PostMapping("/register-snowman")
    // 눈사람 등록 API
    public SnowmanDto.Response postSnowman(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody SnowmanDto.Request request) {

        // 토큰에서 유저 id 가져오기
        String jwtToken = jwtProvider.getTokenFromHeader(authorizationHeader);
        Long userId = jwtProvider.getUserIdFromToken(jwtToken);

        // 새로운 눈사람 db에 저장
        Snowman addedsnowman = snowmanService.addSnowman(userId, request);

        return new SnowmanDto.Response(
                new SnowmanDto.Data(
                        addedsnowman.getSnowmanId(),
                        addedsnowman.getUserId(),
                        addedsnowman.getCategoryId(),
                        addedsnowman.getSnowmanName(),
                        addedsnowman.getRegisterDate(),
                        addedsnowman.getGender(),
                        addedsnowman.getIntroduce(),
                        addedsnowman.getSnsKind(),
                        addedsnowman.getSnsId(),
                        addedsnowman.getIsExpose()),
                201,
                "created");
    }
}