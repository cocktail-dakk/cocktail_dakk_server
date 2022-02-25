package com.cocktail_dakk.config.auth.jwt;

import com.cocktail_dakk.config.auth.dto.UserInfoDto;
import com.cocktail_dakk.src.domain.user.Role;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
public class JwtAuthFilter extends GenericFilterBean {
    private final TokenService tokenService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        if((httpServletRequest.getHeader("user-agent")!=null&&httpServletRequest.getHeader("user-agent").equals("ELB-HealthChecker/2.0"))||isValidURL(httpServletRequest.getRequestURL().toString())) {
            if (httpServletRequest.getHeader("user-agent") != null && !httpServletRequest.getHeader("user-agent").equals("ELB-HealthChecker/2.0"))
                checkRequest(request);

            String token = ((HttpServletRequest) request).getHeader("Auth");

            if (!ObjectUtils.isEmpty(token)) {
                if (tokenService.isTokenExpired(token)) {
                    logger.warn("JWT The Access token is expired");
                    logger.warn("--------------------------------------------------------------------------");
                    throw new JwtException("JWT The Access token is expired");
                }

                if (tokenService.verifyToken(token)) {
                    try {
                        String email = tokenService.getUid(token);
                        String role=tokenService.getRole(token);

                        UserInfoDto userInfoDto = UserInfoDto.builder()
                                .email(email)
                                .role(role)
                                .build();

                        Authentication authentication = getAuthentication(userInfoDto);
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        logger.warn("> Access permitted");
                        logger.warn("--------------------------------------------------------------------------");

                        chain.doFilter(request, response);
                    } catch (ExpiredJwtException e) {
                        logger.warn("JWT The access token is expired and not valid anymore", e);
                        logger.warn("--------------------------------------------------------------------------");
                        throw new JwtException("JWT The access token is expired");
                    } catch (SignatureException e) {
                        logger.warn("JWT Authentication Failed. Username or Password not valid.");
                        logger.warn("--------------------------------------------------------------------------");
                        throw new JwtException("JWT User authentication failed");
                    }
                } else {
                    logger.warn("JWT Invalid access token");
                    logger.warn("--------------------------------------------------------------------------");
                    throw new JwtException("JWT Invalid access token");
                }
            }else {
                if (httpServletRequest.getHeader("user-agent") != null && !httpServletRequest.getHeader("user-agent").equals("ELB-HealthChecker/2.0")) {
                    logger.warn("Empty Token");
                }
                chain.doFilter(request, response);
            }
        }else {
            throw new JwtException("Access Denied");
        }
    }
    public Authentication getAuthentication(UserInfoDto member){
        return new UsernamePasswordAuthenticationToken(member, "", Arrays.asList(new SimpleGrantedAuthority(member.getRole())));
    }

    private void checkRequest(ServletRequest request){
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
        if(headerNames!=null){
            logger.warn("--------------------------------------------------------------------------");
            logger.warn("URL: "+httpServletRequest.getRequestURL().toString());
            logger.warn("Method: "+httpServletRequest.getMethod());
            while (headerNames.hasMoreElements()){
                String name=headerNames.nextElement();
                logger.warn("Header: "+name+" value: "+httpServletRequest.getHeader(name));
            }
            String ip = httpServletRequest.getHeader("X-Forwarded-For");
            logger.warn("> X-FORWARDED-FOR : " + ip);

            if (ip == null) {
                ip = httpServletRequest.getHeader("Proxy-Client-IP");
                logger.warn("> Proxy-Client-IP : " + ip);
            }
            if (ip == null) {
                ip = httpServletRequest.getHeader("WL-Proxy-Client-IP");
                logger.warn(">  WL-Proxy-Client-IP : " + ip);
            }
            if (ip == null) {
                ip = httpServletRequest.getHeader("HTTP_CLIENT_IP");
                logger.warn("> HTTP_CLIENT_IP : " + ip);
            }
            if (ip == null) {
                ip = httpServletRequest.getHeader("HTTP_X_FORWARDED_FOR");
                logger.warn("> HTTP_X_FORWARDED_FOR : " + ip);
            }
            if (ip == null) {
                ip = httpServletRequest.getRemoteAddr();
                logger.warn("> getRemoteAddr : "+ip);
            }
            logger.warn("> Result : IP Address : "+ip);
//            logger.warn("--------------------------------------------------------------------------");
        }
    }

    private boolean isValidURL(String target) {
        String regex1="^http:\\/\\/www.cocktaildakk.shop\\/.*";
        String regex2="^http:\\/\\/localhost:8080\\/.*";

        boolean matches1 = Pattern.matches(regex1, target);
        boolean matches2 = Pattern.matches(regex2, target);

        boolean result=matches1|matches2;

        return result;
    }
}
