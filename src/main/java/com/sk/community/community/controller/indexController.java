package com.sk.community.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by SongKun on 2020/1/19 12:39 下午
 */
@Controller
public class indexController {

    @GetMapping(value = {"/","/index.html","/index"})
    public String index(){
        return "index";
    }
}
