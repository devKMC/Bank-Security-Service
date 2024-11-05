package com.devkmc.Bank_Security_Service.handler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.devkmc.Bank_Security_Service.common.object.CustomOAuth2User;
import com.devkmc.Bank_Security_Service.provider.JwtProvider;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    //토큰 생성을 위함
    private final JwtProvider jwtProvider;

    @Value("${front.host:http://localhost:3000}") // 기본값으로 로컬호스트 사용 나중에 프론트 호스트로 수정하기 
    private String FRONT_HOST;

    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication)
            throws IOException, ServletException {

                CustomOAuth2User oAuth2User = (CustomOAuth2User)authentication.getPrincipal();
                String userId = oAuth2User.getName();

                //위에서 토큰을 의존성주입으로 가져온 것을 사용
                String token = jwtProvider.create(userId);

                response.sendRedirect(FRONT_HOST + "/sns/" + token + "/36000");
    }
}