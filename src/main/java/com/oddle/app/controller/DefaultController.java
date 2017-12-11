package com.oddle.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author : maal (Malaka Gallage)
 * @since : 6/12/17
 */
@Controller
@RequestMapping("/*")
public class DefaultController {

    @RequestMapping(value= "*", method = RequestMethod.GET)
    public String redirect(ModelMap model) {
        return "redirect:/weather";

    }
}
