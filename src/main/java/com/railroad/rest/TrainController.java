package com.railroad.rest;

import com.railroad.core.service.DirectionService;
import com.railroad.core.service.StationService;
import com.railroad.core.service.TrainService;
import com.railroad.model.dto.TrainDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TrainController {

    private final
    TrainService trainService;

    private final
    StationService stationService;

    private final
    DirectionService directionService;

    @Autowired
    public TrainController(TrainService trainService, StationService stationService, DirectionService directionService) {
        this.trainService = trainService;
        this.stationService = stationService;
        this.directionService = directionService;
    }

    /**
     * Process a request and returns ModelAndView for trains.jsp
     *
     * @return model and view.
     */
    @RequestMapping(value = "/train", method = RequestMethod.GET)
    public ModelAndView getAllStations() {
        ModelAndView mav = new ModelAndView("master");
        mav.addObject("trains", trainService.getAllTrains());
        mav.addObject("title", "Trains");
        mav.addObject("userClickTrains", true);
        return mav;
    }

    /**
     * Process a request and returns ModelAndView for train.jsp
     * @param id train id.
     * @return model and view.
     */
    @RequestMapping(value = "/train/{id}", method = RequestMethod.GET)
    public ModelAndView getTrainById(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("master");
        TrainDto trainDto = trainService.getTrainById(id);
        mav.addObject("train", trainDto);
        mav.addObject("stations", stationService.getAllStations());
        mav.addObject("directions", directionService.getAllDirections());
        mav.addObject("wagonTypes", trainService.getAllWagonTypes());
        mav.addObject("title", "Train " + trainDto.getTrainNumber());
        mav.addObject("userClickTrain", true);
        return mav;
    }

    /**
     * Process a request for add
     * new train or update exist train,
     * and returns ModelAndView for train.jsp
     *
     * @param trainDto train DTO.
     * @return model and view.
     */
    @RequestMapping(value = "/train/add", method = RequestMethod.POST)
    public ModelAndView saveOrUpdateTrain(@ModelAttribute("train") TrainDto trainDto) {
        Long id = trainService.saveOrUpdateTrain(trainDto);
        return new ModelAndView("redirect:/train/" + id);
    }
}
