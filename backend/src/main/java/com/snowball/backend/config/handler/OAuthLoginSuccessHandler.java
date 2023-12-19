package com.snowball.backend.config.handler;

import com.snowball.backend.entity.Snowman;
import com.snowball.backend.entity.User;
import com.snowball.backend.config.jwt.JwtProvider;
import com.snowball.backend.service.SnowmanService;
import com.snowball.backend.service.UserService;
import com.snowball.backend.util.StringUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuthLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private static final String REDIRECT_URI = "http://localhost:3000/login?jwt=%s&has_snowman=%s&is_plus=%s";
    private static final long TOKEN_EXPIRATION_TIME = 3600000; // 1시간 동안 유효한 토큰

    private final JwtProvider jwtProvider;
    private final UserService userService;
    private final SnowmanService snowmanService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        final String provider = token.getAuthorizedClientRegistrationId();
        final String accessDate = StringUtil.getCurrentDateAsString();
        String name = "";
        String providerId = "";
        Long userId;

        // 구글 || 카카오 로그인 요청
        if ("google".equals(provider)) {
            log.info("구글 로그인 요청");
            providerId = token.getPrincipal().getAttribute("sub");
            name = token.getPrincipal().getAttribute("name");
        } else if ("kakao".equals(provider)) {
            log.info("카카오 로그인 요청");
            providerId = token.getPrincipal().getAttribute("id").toString();
            name = (String)((Map) token.getPrincipal().getAttribute("properties")).get("nickname");
        }

        User existUser = userService.getUserIdByProviderId(providerId);
        User user = new User();

        // 신규 유저 여부, 따듯한 눈 추가 여부
        boolean hasSnowman = false;
        boolean isPlus = false;

        if (existUser == null) {
            // 신규 유저인 경우
            log.info("신규 유저입니다. 등록을 진행합니다.");
            user.setProvider(provider);
            user.setProviderId(providerId);
            user.setName(name);
            user.setWarmSnow(1L);
            user.setAccessDate(accessDate);
            // 신규 유저 정보 저장
            userService.save(user);
            userId = user.getUserId();
            log.info("유저 id : {}", userId);
        } else {
            // 기존 유저인 경우
            log.info("기존 유저입니다. 업데이트를 진행합니다.");
            userId = existUser.getUserId();
            log.info("유저 id : {}", userId);

            Snowman snowman = snowmanService.getSnowmanByUserId(userId);
            // 이미 눈사람을 등록한 유저인 경우
            if (snowman != null) {
                log.info("이미 눈사람을 등록한 유저입니다.");
                hasSnowman = true;
                // 따듯한 눈을 하나 추가하는 경우
                if (!existUser.getAccessDate().equals(accessDate)) {
                    isPlus = true;
                    existUser.setWarmSnow(existUser.getWarmSnow() + 1);
                    log.info("따듯한 눈이 하나 추가되었습니다. 현재 {}개", existUser.getWarmSnow());
                }
            }
            // 마지막 접속 시간 변경
            existUser.setAccessDate(accessDate);
            // 기존 유저 정보 업데이트
            userService.save(existUser);
        }

        // Jwt 토큰 발급
        String jwtToken = jwtProvider.generateToken(userId, TOKEN_EXPIRATION_TIME);

        // Jwt 토큰을 담아 리다이렉트
        String redirectUri = String.format(REDIRECT_URI, jwtToken, hasSnowman, isPlus);
        getRedirectStrategy().sendRedirect(request, response, redirectUri);
    }
}