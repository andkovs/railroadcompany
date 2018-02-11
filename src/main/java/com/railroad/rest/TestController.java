package com.railroad.rest;

import com.railroad.core.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {

    @Autowired
    TestService testService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ModelAndView getAllTests() {
        ModelAndView mav = new ModelAndView("test");
        mav.addObject("tests", testService.getAllTests());
        return mav;
    }

}
