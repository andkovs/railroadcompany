package com.railroad.controller;

import com.railroad.core.service.ScheduleService;
import com.railroad.core.service.StationService;
import com.railroad.model.dto.ScheduleAddDto;
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
public class ScheduleController {

    private static final Logger logger = Logger.getLogger(ScheduleController.class);

    private final
    ScheduleService scheduleService;

    private final
    StationService stationService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService, StationService stationService) {
        this.scheduleService = scheduleService;
        this.stationService = stationService;
    }

    /**
     * Process a request and returns ModelAndView for schedule.jsp.
     *
     * @param id        station id.
     * @param principal security principal.
     * @return model and view.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView getScheduleListByStationId(@PathVariable("id") Long id, Principal principal) {
        String userName = "Guest";
        if (principal != null) {
            userName = principal.getName();
        }
        logger.info(userName + " entering method getScheduleListByStationId()");

        ModelAndView mav = new ModelAndView("master");
        mav.addObject("stations", stationService.getAllStations());
        mav.addObject("station", stationService.getStationById(id));
        mav.addObject("schedules", scheduleService.getScheduleListByDepStationId(id));
        mav.addObject("title", "Schedule");
        mav.addObject("userClickHome", true);
        return mav;
    }

    /**
     * Process a request for add
     * new schedule
     * and returns ModelAndView for train.jsp.
     *
     * @param scheduleAddDto schedule's DTO for add in DB.
     * @param red            redirect attribute.
     * @param principal      security principal.
     * @return model and view.
     */
    @RequestMapping(value = "/schedule/add", method = RequestMethod.POST)
    public ModelAndView saveSchedule(ScheduleAddDto scheduleAddDto, RedirectAttributes red, Principal principal) {
        String userName = "Guest";
        if (principal != null) {
            userName = principal.getName();
        }
        logger.info(userName + " entering method saveSchedule()");

        scheduleService.saveSchedule(scheduleAddDto);
        red.addFlashAttribute("tabpanel", false);
        return new ModelAndView("redirect:/train/" + scheduleAddDto.getTrainId());
    }

    /**
     * Process a request for remove schedule
     * and returns ModelAndView for train.jsp
     *
     * @param id        removed schedule id.
     * @param red       redirect attribute.
     * @param principal security principal.
     * @return model and view.
     */
    @RequestMapping(value = "/schedule/{id}/delete", method = RequestMethod.GET)
    public ModelAndView removeSchedule(@PathVariable("id") Long id, RedirectAttributes red, Principal principal) {
        String userName = "Guest";
        if (principal != null) {
            userName = principal.getName();
        }
        logger.info(userName + " entering method removeSchedule()");

        Long trainId = scheduleService.getTrainIdByScheduleId(id);
        scheduleService.removeScheduleById(id);
        red.addFlashAttribute("tabpanel", false);
        return new ModelAndView("redirect:/train/" + trainId);
    }
}
