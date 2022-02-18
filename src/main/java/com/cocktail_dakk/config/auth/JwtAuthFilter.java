package com.cocktail_dakk.config.auth;

import com.cocktail_dakk.config.auth.dto.UserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;

@RequiredArgsConstructor
public class JwtAuthFilter extends GenericFilterBean {
    private final TokenService tokenService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token=((HttpServletRequest)request).getHeader("Auth");

                if(token!=null&&tokenService.verifyToken(token)){
                    String email=tokenService.getUid(token);

                    UserInfoDto userInfoDto = UserInfoDto.builder()
                            .email(email)
                            .build();

                    Authentication authentication = getAuthentication(userInfoDto);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
                chain.doFilter(request, response);
    }
    public Authentication getAuthentication(UserInfoDto member){
        return new UsernamePasswordAuthenticationToken(member, "", Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
