package org.hae.sfaas.global.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //이 클래스가 Spring 설정 파일임을 등록
public class SecurityConfig {

    /**
     * @Bean 어노테이션으로 이 메서드가 반환하는 객체를 Spring 컨텍스트 빈(Bean)에 등록
     * 여기서는 SecurityFilterChain을 반환하여 Spring Security의 보안 필터 체인 구성
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // CORS 설정 추가
                .cors(cors -> cors.configurationSource(
                                request -> {
                                    var corsConfiguration = new org.springframework.web.cors.CorsConfiguration();
                                    corsConfiguration.addAllowedOriginPattern("*"); //모든 도메인 허용
                                    corsConfiguration.addAllowedMethod("*"); //모든 HTTP 메서드 허용
                                    corsConfiguration.addAllowedHeader("*"); // 모든 헤더 허용
                                    corsConfiguration.setAllowCredentials(true); // 쿠키 허용
                                    return corsConfiguration;
                                }
                        )

                )
                //CSRF 설정 비활성화
                .csrf(csrf -> csrf.disable());
        return http.build();
    }

}