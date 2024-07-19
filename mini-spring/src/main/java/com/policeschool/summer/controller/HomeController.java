package com.policeschool.summer.controller;


import com.policeschool.summer.annotation.Autowired;
import com.policeschool.summer.annotation.Controller;
import com.policeschool.summer.annotation.RequestMapping;
import com.policeschool.summer.service.HomeService;

/**
 * @author ljx
 */
@Controller("homeController")
@RequestMapping("/home")
public class HomeController {

    @Autowired("homeServiceImpl")
    private HomeService homeService;

    @RequestMapping("/index")
    public void index() {
        homeService.index();
    }
}
