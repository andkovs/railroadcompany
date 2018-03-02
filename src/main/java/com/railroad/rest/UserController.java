package com.railroad.rest;

import com.railroad.core.service.UserService;
import com.railroad.model.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    private final
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Process a request and returns ModelAndView for user.jsp.
     * @param login user login.
     * @return model and view.
     */
    @RequestMapping(value = "/user/{login}", method = RequestMethod.GET)
    public ModelAndView getUserById(@PathVariable("login") String login) {
        ModelAndView mav = new ModelAndView("master");
        UserDto userDto = userService.getUserByLogin(login);
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
     * @param userDto user DTO.
     * @return model and view.
     */
    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public ModelAndView saveOrUpdateUser(UserDto userDto, RedirectAttributes red) {
        userService.saveOrUpdateUser(userDto);
        red.addFlashAttribute("msg", "Success registration. Sing in, please");
        return new ModelAndView("redirect:/login");
    }
}
