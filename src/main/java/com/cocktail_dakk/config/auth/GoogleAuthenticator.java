//package com.cocktail_dakk.config.auth;
//
//import com.google.api.client.json.jackson2.JacksonFactory;
//import lombok.AllArgsConstructor;
//import org.apache.catalina.Authenticator;
//import org.apache.catalina.connector.Request;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//@AllArgsConstructor
//public class GoogleAuthenticator implements Authenticator {
//    private final JacksonFactory jacksonFactory;
//
//    @Override
//    public boolean authenticate(Request request, HttpServletResponse response) throws IOException {
//        return false;
//    }
//
//    @Override
//    public void login(String userName, String password, Request request) throws ServletException {
//
//    }
//
//    @Override
//    public void logout(Request request) {
//
//    }
//}
