package com.cocktail_dakk.config.auth.jwt;

import com.cocktail_dakk.config.BaseResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestController
public class TokenController {
    private final TokenService tokenService;

    @GetMapping("/token/refresh")
    public BaseResponse<String> refreshAuth(HttpServletRequest request, HttpServletResponse response){
        String token=request.getHeader("Refresh");

        if(!ObjectUtils.isEmpty(token)){
            try {
                if(tokenService.isTokenExpired(token))
                    throw new JwtException("JWT The refresh token has expired, so log in again");
            }catch (JwtException e){
                return new BaseResponse<>((e.getMessage()));
            }

            if(tokenService.verifyToken(token)){
                try{
                    String email=tokenService.getUid(token);
                    Token newToken=tokenService.generateToken(email, "USER");

                    response.addHeader("Auth", newToken.getToken());
                    response.addHeader("Refresh", newToken.getRefreshToken());
                    response.setContentType("application/json;charset=UTF-8");

                    return new BaseResponse<>("NEW TOKEN");
                }catch (ExpiredJwtException e) {
                    JwtException exception = new JwtException("JWT The refresh token has expired, so log in again");
                    return new BaseResponse<>((exception.getMessage()));
                } catch(SignatureException e){
                    JwtException exception = new JwtException("JWT The refresh token has signature exception. User authentication failed");
                    return new BaseResponse<>((exception.getMessage()));
                }
            }else{
                return new BaseResponse<>("JWT The refresh token has expired, so log in again");
            }
        }else{
            return new BaseResponse<>("JWT Invalid refresh token");
        }
    }
}
