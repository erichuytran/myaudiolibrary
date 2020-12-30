package com.myaudiolibrary.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping(value = "/")
    public String displayAccueil(final ModelMap model) {
        return "accueil";
    }

}
