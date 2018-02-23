package com.railroad.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MasterController {

    /**
     * Process a request and returns ModelAndView for home.jsp
     *
     * @return model and view.
     */
    @RequestMapping(value = {"/", "/home", "/index"})
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("master");
        mav.addObject("title", "Home");
        mav.addObject("userClickHome", true);
        return mav;
    }
}
