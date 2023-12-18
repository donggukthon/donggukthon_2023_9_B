package com.snowball.backend.controller;

import com.snowball.backend.config.jwt.JwtProvider;
import com.snowball.backend.dto.SnowmanAllDto;
import com.snowball.backend.dto.SnowmanDto;
import com.snowball.backend.dto.MyInfoDto;
import com.snowball.backend.entity.Snowman;
import com.snowball.backend.entity.User;
import com.snowball.backend.service.SnowmanService;

import com.snowball.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class SnowmanController {
    private final SnowmanService snowmanService;
    private final UserService userService;
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

    @GetMapping("/view-snowman")
    // 본인 눈사람 조회 API
    public MyInfoDto.Response getMySnowman(
            @RequestHeader("Authorization") String authorizationHeader) {

        // 토큰에서 유저 id 가져오기
        String jwtToken = jwtProvider.getTokenFromHeader(authorizationHeader);
        Long userId = jwtProvider.getUserIdFromToken(jwtToken);

        // 사용자의 눈사람 찾아오기
        Snowman getSnowman = snowmanService.getSnowmanBySnowmanId(userId);

        // 사용자 정보 찾아오기
        User getUser = userService.getUserIdByProviderId(userId.toString());

        return new MyInfoDto.Response(
                new MyInfoDto.Data(
                        getSnowman.getSnowmanId(),
                        getSnowman.getUserId(),
                        getSnowman.getCategoryId(),
                        getSnowman.getSnowmanName(),
                        getSnowman.getRegisterDate(),
                        getSnowman.getGender(),
                        getSnowman.getIntroduce(),
                        getSnowman.getSnsKind(),
                        getSnowman.getSnsId(),
                        getSnowman.getIsExpose(),
                        getUser.getWarmSnow()),
                200,
                "Success");
    }

    @PostMapping("/view-snowman/other")
    // 타인 눈사람 조회 API
    public SnowmanDto.Response viewOther(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody SnowmanDto.Request request) {

        // 요청된 눈사람 id 가져오기
        Long snowmanId = request.getSnowman_id();

        // 다른 사람 눈사람 정보 가져오기
        Snowman getSnowman = snowmanService.getSnowmanBySnowmanId(snowmanId);

        return new SnowmanDto.Response(
                new SnowmanDto.Data(
                        getSnowman.getSnowmanId(),
                        getSnowman.getUserId(),
                        getSnowman.getCategoryId(),
                        getSnowman.getSnowmanName(),
                        getSnowman.getRegisterDate(),
                        getSnowman.getGender(),
                        getSnowman.getIntroduce(),
                        getSnowman.getSnsKind(),
                        getSnowman.getSnsId(),
                        getSnowman.getIsExpose()),
                200,
                "Success");
    }

    @GetMapping("/view-snowman/other")
    // 모든 눈사람 조회 API
    public SnowmanAllDto.Data viewAll(
            @RequestHeader("Authorization") String authorizationHeader) {

        // 요청된 눈사람 id 가져오기
        List<Snowman> snowmanList = snowmanService.getSnowmanAll();

        return new SnowmanAllDto.Data(snowmanList);
    }

    @GetMapping("/view-snowman/category")
    // 카테고리별 눈사람 조회 API
    public SnowmanAllDto.Data viewCategory(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestParam("category_id") Long categoryId) {

        // 요청된 눈사람 id 가져오기
        List<Snowman> snowmanList = snowmanService.getSnowmanByCategoryID(categoryId);

        return new SnowmanAllDto.Data(snowmanList);
    }


}
