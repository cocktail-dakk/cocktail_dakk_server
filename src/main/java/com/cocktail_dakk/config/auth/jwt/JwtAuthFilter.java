package com.cocktail_dakk.config.auth.jwt;

import com.cocktail_dakk.config.auth.dto.UserInfoDto;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;

@RequiredArgsConstructor
public class JwtAuthFilter extends GenericFilterBean {
    private final TokenService tokenService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token=((HttpServletRequest)request).getHeader("Auth");

        if(!ObjectUtils.isEmpty(token)){
            if(tokenService.isTokenExpired(token)){
                throw new JwtException("JWT The Access token is expired");
            }

            if(tokenService.verifyToken(token)){
                try{
                    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
                    Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
                    if(headerNames!=null){
                        logger.warn("--------------------------------------------------------------------------");
                        while (headerNames.hasMoreElements()){
                            String name=headerNames.nextElement();
                            logger.warn("Header: "+name+" value: "+httpServletRequest.getHeader(name));
                        }
                        logger.warn("--------------------------------------------------------------------------");
                    }
                    String email=tokenService.getUid(token);

                    UserInfoDto userInfoDto = UserInfoDto.builder()
                            .email(email)
                            .build();

                    Authentication authentication = getAuthentication(userInfoDto);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }catch (ExpiredJwtException e) {
                    logger.warn("JWT The access token is expired and not valid anymore", e);
                    throw new JwtException("JWT The access token is expired");
                } catch(SignatureException e){
                    logger.error("JWT Authentication Failed. Username or Password not valid.");
                    throw new JwtException("JWT User authentication failed");
                }
            }else{
                throw new JwtException("JWT Invalid access token");
            }
        }
        chain.doFilter(request, response);
    }
    public Authentication getAuthentication(UserInfoDto member){
        return new UsernamePasswordAuthenticationToken(member, "", Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
