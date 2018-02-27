package com.railroad.rest;

import com.railroad.core.service.StationService;
import com.railroad.model.dto.StationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StationController {

    private final
    StationService stationService;

    @Autowired
    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    /**
     * Process a request and returns ModelAndView for stations.jsp.
     *
     * @return model and view.
     */
    @RequestMapping(value = "/station", method = RequestMethod.GET)
    public ModelAndView getAllStations() {
        ModelAndView mav = new ModelAndView("master");
        mav.addObject("stations", stationService.getAllStations());
        mav.addObject("neighbouringStations", stationService.getAllNeighbouringStation());
        mav.addObject("title", "Stations");
        mav.addObject("userClickStations", true);
        return mav;
    }

    /**
     * Process a request and returns ModelAndView for station.jsp.
     * @param id station id.
     * @return model and view.
     */
    @RequestMapping(value = "/station/{id}", method = RequestMethod.GET)
    public ModelAndView getStationById(@PathVariable("id") Long id) {
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
     * and returns ModelAndView for station.jsp
     *
     * @param stationDto station DTO.
     * @return model and view.
     */
    @RequestMapping(value = "/station/add", method = RequestMethod.POST)
    public ModelAndView saveOrUpdateStation(@ModelAttribute("station") StationDto stationDto) {
        Long id = stationService.saveOrUpdateStation(stationDto);
        return new ModelAndView("redirect:/station/" + id);
    }

    /**
     * Process a request for remove station
     * and returns ModelAndView for station.jsp
     *
     * @return model and view.
     */
    @RequestMapping(value = "/station/{id}/delete", method = RequestMethod.GET)
    public ModelAndView removeStation(@PathVariable("id") Long id) {
        stationService.removeStationById(id);
        return new ModelAndView("redirect:/station/");
    }


}
