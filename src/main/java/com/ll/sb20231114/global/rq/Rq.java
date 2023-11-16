package com.ll.sb20231114.global.rq;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

//@ApplicationScope // 객체 수명 무한
@RequestScope //매번 만들어졌다 사라지게 한다.
@Component // 객체 수명 무한
public class Rq {
    private final HttpServletRequest req;
    private final HttpServletResponse rsep;

    public Rq(HttpServletRequest req, HttpServletResponse rsep) {
        this.req = req;
        this.rsep = rsep;
    }
}
