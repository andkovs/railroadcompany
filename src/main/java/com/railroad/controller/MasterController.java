package com.railroad.controller;

import com.railroad.core.service.StationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;


@Controller
public class MasterController {

    private static final Logger logger = Logger.getLogger(MasterController.class);

    private final
    StationService stationService;

    @Autowired
    public MasterController(StationService stationService) {
        this.stationService = stationService;
    }

    /**
     * Process a request and returns ModelAndView for home.jsp.
     *
     * @param principal security principal.
     * @return model and view.
     */
    @RequestMapping(value = {"/", "/home", "/index"})
    public ModelAndView index(Principal principal) {
        String userName = "Guest";
        if (principal != null) {
            userName = principal.getName();
        }
        logger.info(userName + " entering method index()");

        ModelAndView mav = new ModelAndView("master");
        mav.addObject("title", "Home");
        mav.addObject("stations", stationService.getAllStations());
        mav.addObject("userClickHome", true);
        return mav;
    }

    /**
     * Process a request and returns ModelAndView for login.jsp.
     *
     * @param error     error string
     * @param logout    logout string
     * @param principal security principal.
     * @return model and view.
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout,
                              Principal principal) {
        String userName = "Guest";
        if (principal != null) {
            userName = principal.getName();
        }
        logger.info(userName + " entering method login()");

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
