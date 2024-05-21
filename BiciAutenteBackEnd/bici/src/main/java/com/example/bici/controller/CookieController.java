package com.example.bici.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CookieController {

    @GetMapping("/set-cookie")
    public ResponseEntity<String> setCookie(HttpServletResponse response) {

        Cookie cookie = new Cookie("cookie", "value");
        cookie.setMaxAge(3600); //Define a validade de cookie em segundos
        response.addCookie(cookie);
        return ResponseEntity.ok("Set cookie");

    }
}
