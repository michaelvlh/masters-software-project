package com.team12.foodforall.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * @author: Abhishek M Nagesh
 * @date: 26/03/2022 :12:43
 **/

@Controller
public class IndexController {

    @GetMapping("/index")
    public String _index() {
        //TODO:

        return "/projects";
    }
}