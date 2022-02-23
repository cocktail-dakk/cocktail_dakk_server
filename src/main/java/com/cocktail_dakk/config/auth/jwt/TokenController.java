package com.cocktail_dakk.config.auth.jwt;

import com.cocktail_dakk.config.BaseResponse;
import com.cocktail_dakk.config.BaseResponseStatus;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.cocktail_dakk.config.BaseResponseStatus.*;

@RequiredArgsConstructor
@RestController
public class TokenController {
    private final TokenService tokenService;

    @GetMapping("/token/refresh")
    public BaseResponse<Token> refreshAuth(HttpServletRequest request, HttpServletResponse response){
        String token=request.getHeader("Refresh");

        if(!ObjectUtils.isEmpty(token)){
            try {
                if(tokenService.isTokenExpired(token))
                    throw new JwtException("JWT The refresh token has expired, so log in again");
            }catch (JwtException e){
                return new BaseResponse<>(JWT_REFRESH_TOKEN_EXPIRED_ERROR);
            }

            if(tokenService.verifyToken(token)){
                try{
                    String email=tokenService.getUid(token);
                    Token newToken=tokenService.generateToken(email, "USER");

                    return new BaseResponse<>(newToken);
                }catch (ExpiredJwtException e) {
                    return new BaseResponse<>(JWT_REFRESH_TOKEN_EXPIRED_ERROR);
                } catch(SignatureException e){
                    return new BaseResponse<>(JWT_REFRESH_TOKEN_SIGNATURE_ERROR);
                }
            }else{
                return new BaseResponse<>(JWT_REFRESH_TOKEN_EXPIRED_ERROR);
            }
        }else{
            return new BaseResponse<>(JWT_REFRESH_TOKEN_EMPTY_ERROR);
        }
    }
}
