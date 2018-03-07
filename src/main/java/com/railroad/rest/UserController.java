package com.railroad.rest;

import com.railroad.core.service.UserService;
import com.railroad.model.dto.UserDto;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class UserController {

    private static final Logger logger = Logger.getLogger(MasterController.class);

    private final
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Process a request and returns ModelAndView for user.jsp.
     *
     * @param login     user login.
     * @param principal security principal.
     * @return model and view.
     */
    @RequestMapping(value = "/user/{login}", method = RequestMethod.GET)
    public ModelAndView getUserById(@PathVariable("login") String login, Principal principal) {
        String userName = "Guest";
        if (principal != null) {
            userName = principal.getName();
        }
        logger.info(userName + " entering method getUserById()");

        ModelAndView mav = new ModelAndView("master");
        UserDto userDto = userService.getUserByLogin(login);
        if (userDto == null) {
            new ModelAndView("redirect:/login");
        }
        mav.addObject("user", userDto);
        mav.addObject("title", "User " + login);
        mav.addObject("userClickUser", true);
        return mav;
    }

    /**
     * Process a request for add
     * new user or update
     * and returns ModelAndView for user.jsp
     *
     * @param userDto   user DTO.
     * @param red       redirect attribute.
     * @param principal security principal.
     * @return model and view.
     */
    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public ModelAndView saveOrUpdateUser(UserDto userDto, RedirectAttributes red, Principal principal) {
        String userName = "Guest";
        if (principal != null) {
            userName = principal.getName();
        }
        logger.info(userName + " entering method saveOrUpdateUser()");

        String msg = userService.saveOrUpdateUser(userDto);
        if (msg == null) {
            red.addFlashAttribute("error", "Something went wrong! Perhaps this login already exists.");
            return new ModelAndView("redirect:/user/0");
        }
        if (msg.equals("added")) {
            red.addFlashAttribute("msg", "Success registration. Sing in, please");
            return new ModelAndView("redirect:/login");
        }
        if (msg.equals("updated")) {
            red.addFlashAttribute("msg", "Data changed successfully");
            return new ModelAndView("redirect:/user/" + principal.getName());
        }

        return new ModelAndView("redirect:/login");
    }
}
