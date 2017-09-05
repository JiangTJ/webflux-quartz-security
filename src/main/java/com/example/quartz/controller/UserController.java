package com.example.quartz.controller;

import com.example.quartz.dao.UserDao;
import com.example.quartz.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/9/5.
 */
@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/me")
    public Mono<Map<String,String>> me(@AuthenticationPrincipal UserDetails user) {
        return me(Mono.just(user));
    }

    @GetMapping("/u")
    public Mono<User> u() {
        User user = userDao.getUser();
        return Mono.just(user);
    }

    @GetMapping("/mono/me")
    public Mono<Map<String,String>> me(@AuthenticationPrincipal Mono<UserDetails> user) {
        return user.flatMap( u -> Mono.just(Collections.singletonMap("username", u.getUsername())));
    }

    @GetMapping("/mono/session")
    public Mono<Map<String,Object>> Session(Mono<WebSession> session) {
        return session.flatMap( s -> Mono.just(s.getAttributes()));
    }

    @GetMapping("/principal")
    public Mono<Map<String,String>> principal(Principal principal) {
        return principal(Mono.just(principal));
    }

    @GetMapping("/mono/principal")
    public Mono<Map<String,String>> principal(Mono<Principal> principal) {
        return principal.flatMap( p -> Mono.just(Collections.singletonMap("username", p.getName())));
    }

    @GetMapping("/admin")
    public Map<String,String> admin() {
        return Collections.singletonMap("isadmin", "true");
    }
}