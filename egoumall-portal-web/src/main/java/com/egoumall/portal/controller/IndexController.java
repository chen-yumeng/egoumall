package com.egoumall.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: egoumall->IndexController
 * @description: 首页展示控制器
 * @author: cg
 * @create: 2020-01-29 16:41
 **/
@Controller
public class IndexController {

    @RequestMapping("/")
    public String showIndex() {
        return "index";
    }

}
