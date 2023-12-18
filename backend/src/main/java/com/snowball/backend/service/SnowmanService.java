package com.snowball.backend.service;

import com.snowball.backend.dto.SnowmanDto;
import com.snowball.backend.entity.Snowman;
import com.snowball.backend.repository.SnowmanRepository;
import com.snowball.backend.util.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SnowmanService {

    private final SnowmanRepository snowmanRepository;

    // 눈사람 저장 또는 업데이트
    public void save(Snowman snowman) {
        snowmanRepository.save(snowman);
    }

    // 눈사람 id로 눈사람을 찾아 반환
    public Snowman getSnowmanBySnowmanId(Long snowmanId) {
        return snowmanRepository.findSnowmanBySnowmanId(snowmanId);
    }

    // 유저 id로 눈사람을 찾아 반환
    public Snowman getSnowmanByUserId(Long userId) {
        return snowmanRepository.findSnowmanByUserId(userId);
    }

    // 새로운 눈사람을 등록
    public Snowman addSnowman(Long userId, SnowmanDto.Request request) {

        Snowman snowman = new Snowman();
        snowman.setUserId(userId);
        snowman.setCategoryId(request.getCategory_id());
        snowman.setSnowmanName(request.getSnowman_name());
        snowman.setRegisterDate(StringUtil.getCurrentDateAsString());
        snowman.setGender(request.getGender());
        snowman.setIntroduce(request.getIntroduce());
        snowman.setSnsKind(request.getSns_kind());
        snowman.setSnsId(request.getSns_id());
        snowman.setIsExpose(request.getIs_expose());

        save(snowman);

        return snowman;
    }
}