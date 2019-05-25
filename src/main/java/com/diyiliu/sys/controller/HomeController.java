package com.diyiliu.sys.controller;

import com.diyiliu.support.util.ResponseUtil;
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
        Subject subject = SecurityUtils.getSubject();

        return ResponseUtil.ok(subject.getSession().getId());
    }

    @PostMapping("/login")
    public Object login(HttpServletRequest request, String username, String password){
        String failure = (String) request.getAttribute("shiroLoginFailure");
        Subject subject = SecurityUtils.getSubject();

        System.out.println(subject.isAuthenticated() + ":" + failure);
        return ResponseUtil.unLogin();
    }

    @GetMapping("/login")
    public Object toLogin(){

        return ResponseUtil.unLogin();
    }

    @GetMapping("/home")
    public Object home(){

        return ResponseUtil.ok("欢迎");
    }
}
