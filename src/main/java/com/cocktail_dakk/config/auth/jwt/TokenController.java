package com.cocktail_dakk.config.auth.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestController
public class TokenController {
    private final TokenService tokenService;

    @GetMapping("/token/refresh")
    public String refreshAuth(HttpServletRequest request, HttpServletResponse response){
        String token=request.getHeader("Refresh");

        if(token!=null&& tokenService.verifyToken(token)){
            String email=tokenService.getUid(token);
            Token newToken=tokenService.generateToken(email, "USER");

            response.addHeader("Auth", newToken.getToken());
            response.addHeader("Refresh", newToken.getRefreshToken());
            response.setContentType("application/json;charset=UTF-8");

            return "NEW TOKEN";
        }else{
            return "The refresh token has expired, so log in again";
        }
    }
}
