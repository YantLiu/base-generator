package com.base.generator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-06-06 13:34
 */
@Controller
@RequestMapping("")
public class HomeController{


    @GetMapping("index")
    public String index(Map<String,Object> map){
        return "index";
    }

    @GetMapping("about")
    public String about(){
        return "about";
    }
    @GetMapping("generator")
    public String user(){
        return "generator/list";
    }
}
