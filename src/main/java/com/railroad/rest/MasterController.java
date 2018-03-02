package com.railroad.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView mav = new ModelAndView();
        if (error != null) {
            mav.addObject("error", "Invalid username and password!");
        }
        if (logout != null) {
            mav.addObject("msg", "You've been logged out successfully.");
        }
        mav.setViewName("master");
        mav.addObject("userClickLogin", true);
        return mav;

    }
}
