package com.diyiliu.sys.controller;

import com.diyiliu.support.util.RespUtil;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/**
 * Description: HomeController
 * Author: DIYILIU
 * Update: 2019-05-23 09:25
 */


@Api(description = "首页")
@RequestMapping("/console")
@RestController
public class HomeController {

    @GetMapping
    public Object index(){

        return RespUtil.ok("Hello, Vue.js!");
    }

    @PostMapping("/login")
    public Object login(HttpServletRequest request, String username, String password){
        String failure = (String) request.getAttribute("shiroLoginFailure");

        Subject subject = SecurityUtils.getSubject();

        System.out.println(subject.isAuthenticated());

        return RespUtil.ok(subject.getSession().getId());
    }

    @GetMapping("/login")
    public Object toLogin(){

        return RespUtil.unLogin();
    }

    @GetMapping("/home")
    public Object home(){

        return RespUtil.ok("欢迎");
    }
}
