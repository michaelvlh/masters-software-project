package com.team12.foodforall.controller.common;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactusController {

    @GetMapping("/contact-us")
    public String _contactus() {
        //TODO:

        return "contact-us";
    }
}
