package com.railroad.controller;

import com.railroad.core.service.StationService;
import com.railroad.model.dto.StationDto;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class StationController {

    private static final Logger logger = Logger.getLogger(MasterController.class);

    private final
    StationService stationService;

    @Autowired
    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    /**
     * Process a request and returns ModelAndView for stations.jsp.
     *
     * @param principal security principal.
     * @return model and view.
     */
    @RequestMapping(value = "/station", method = RequestMethod.GET)
    public ModelAndView getAllStations(Principal principal) {
        String userName = "Guest";
        if (principal != null) {
            userName = principal.getName();
        }
        logger.info(userName + " entering method getAllStations()");

        ModelAndView mav = new ModelAndView("master");
        mav.addObject("stations", stationService.getAllStations());
        mav.addObject("neighbouringStations", stationService.getAllNeighbouringStation());
        mav.addObject("title", "Stations");
        mav.addObject("userClickStations", true);
        return mav;
    }

    /**
     * Process a request and returns ModelAndView for station.jsp.
     *
     * @param id        station id.
     * @param principal security principal.
     * @return model and view.
     */
    @RequestMapping(value = "/station/{id}", method = RequestMethod.GET)
    public ModelAndView getStationById(@PathVariable("id") Long id, Principal principal) {
        String userName = "Guest";
        if (principal != null) {
            userName = principal.getName();
        }
        logger.info(userName + " entering method getStationById()");

        ModelAndView mav = new ModelAndView("master");
        StationDto stationDto = stationService.getStationById(id);
        mav.addObject("station", stationDto);
        mav.addObject("stations", stationService.getAllStationsWithoutCurrent(id));
        mav.addObject("neighbouringStations", stationService.getAllNeighbouringStationWithoutCurrent(id));
        mav.addObject("title", "Station " + stationDto.getStationTitle());
        mav.addObject("userClickStation", true);
        return mav;
    }

    /**
     * Process a request for add
     * new station or update
     * and returns ModelAndView for station.jsp.
     *
     * @param stationDto station's DTO.
     * @param red        redirect attribute.
     * @param principal  security principal.
     * @return model and view.
     */
    @RequestMapping(value = "/station/add", method = RequestMethod.POST)
    public ModelAndView saveOrUpdateStation(@ModelAttribute("station") StationDto stationDto, RedirectAttributes red, Principal principal) {
        String userName = "Guest";
        if (principal != null) {
            userName = principal.getName();
        }
        logger.info(userName + " entering method saveOrUpdateStation()");


        Long id = stationService.saveOrUpdateStation(stationDto);
        if (id == null) {
            red.addFlashAttribute("msg", "Something went wrong! Perhaps this title of the station already exists.");
            return new ModelAndView("redirect:/station/" + 0);
        }
        return new ModelAndView("redirect:/station/" + id);
    }

    /**
     * Process a request for remove station
     * and returns ModelAndView for station.jsp.
     *
     * @param id        station id.
     * @param principal security principal.
     * @return model and view.
     */
    @RequestMapping(value = "/station/{id}/delete", method = RequestMethod.GET)
    public ModelAndView removeStation(@PathVariable("id") Long id, Principal principal) {
        String userName = "Guest";
        if (principal != null) {
            userName = principal.getName();
        }
        logger.info(userName + " entering method removeStation()");

        stationService.removeStationById(id);
        return new ModelAndView("redirect:/station/");
    }
}
