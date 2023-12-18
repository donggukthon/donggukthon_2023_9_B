package com.snowball.backend.util;

import org.springframework.stereotype.Component;

@Component
public class TestResultUtil {

    // 테스트 결과를 기준으로 카테고리 id를 반환하는 메소드
    public Long getCategoryIdByTestResult(String testResult) {
        final int T = Integer.parseInt(testResult.substring(0, 1));
        final int F = Integer.parseInt(testResult.substring(1, 2));
        final int E = Integer.parseInt(testResult.substring(2, 3));
        final int I = Integer.parseInt(testResult.substring(3, 4));
        final int X = Integer.parseInt(testResult.substring(4, 5));
        final int O = Integer.parseInt(testResult.substring(5, 6));

        Long category_id;
        // 결과 도출
        if (T > 1) {
            if (E > 1) {
                if (X > 1) {
                    // 스포티한 눈썰매형
                    category_id = 7L;
                } else {
                    // 베이지톤의 트렌치코트형
                    category_id = 6L;
                }
            } else {
                if (X > 1) {
                    // 날이 선 얼음 덩어리형
                    category_id = 3L;
                } else {
                    // 차가운 색감의 벨벳 머플러형
                    category_id = 2L;
                }
            }
        } else {
            if (E > 1) {
                if (X > 1) {
                    // 얼음이 들어간 핫초코형
                    category_id = 4L;
                } else {
                    // 포근한 극세사 담요형
                    category_id = 1L;
                }
            } else {
                if (X > 1) {
                    // 눈송이 벙어리장갑형
                    category_id = 8L;
                } else {
                    // 소라게의 베레모형
                    category_id = 5L;
                }
            }
        }

        return category_id;
    }
}
