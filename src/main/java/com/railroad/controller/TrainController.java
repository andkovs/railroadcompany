package com.railroad.controller;

import com.railroad.core.service.DirectionService;
import com.railroad.core.service.StationService;
import com.railroad.core.service.TrainService;
import com.railroad.model.dto.TrainDto;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class TrainController {

    private static final Logger logger = Logger.getLogger(MasterController.class);

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
     * @param principal security principal.
     * @return model and view.
     */
    @RequestMapping(value = "/train", method = RequestMethod.GET)
    public ModelAndView getAllStations(Principal principal) {
        String userName = "Guest";
        if (principal != null) {
            userName = principal.getName();
        }
        logger.info(userName + " entering method getAllStations()");

        ModelAndView mav = new ModelAndView("master");
        mav.addObject("trains", trainService.getAllTrains());
        mav.addObject("title", "Trains");
        mav.addObject("userClickTrains", true);
        return mav;
    }

    /**
     * Process a request and returns ModelAndView for train.jsp
     *
     * @param id        train id.
     * @param principal security principal.
     * @return model and view.
     */
    @RequestMapping(value = "/train/{id}", method = RequestMethod.GET)
    public ModelAndView getTrainById(@PathVariable("id") Long id, Principal principal) {
        String userName = "Guest";
        if (principal != null) {
            userName = principal.getName();
        }
        logger.info(userName + " entering method getTrainById()");

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
     * @param trainDto  train DTO.
     * @param red       redirect attribute.
     * @param principal security principal.
     * @return model and view.
     */
    @RequestMapping(value = "/train/add", method = RequestMethod.POST)
    public ModelAndView saveOrUpdateTrain(@ModelAttribute("train") TrainDto trainDto, RedirectAttributes red, Principal principal) {
        String userName = "Guest";
        if (principal != null) {
            userName = principal.getName();
        }
        logger.info(userName + " entering method saveOrUpdateTrain()");

        Long id = trainService.saveOrUpdateTrain(trainDto);
        if (id == null) {
            red.addFlashAttribute("msg", "Something went wrong! Perhaps this number of the train already exists.");
            return new ModelAndView("redirect:/train/" + 0);
        }
        return new ModelAndView("redirect:/train/" + id);
    }

    /**
     * Process a request for remove train
     * and returns ModelAndView for trains.jsp
     *
     * @param id        removed train id.
     * @param principal security principal.
     * @return model and view.
     */
    @RequestMapping(value = "/train/{id}/delete", method = RequestMethod.GET)
    public ModelAndView removeTrain(@PathVariable("id") Long id, Principal principal) {
        String userName = "Guest";
        if (principal != null) {
            userName = principal.getName();
        }
        logger.info(userName + " entering method removeTrain()");

        trainService.removeTrainById(id);
        return new ModelAndView("redirect:/train/");
    }

    /**
     * Process a request for enabled/disabled train
     * and returns ModelAndView for trains.jsp
     *
     * @param id        enabled/disabled train id.
     * @param principal security principal.
     * @return model and view.
     */
    @RequestMapping(value = "/train/{id}/enable", method = RequestMethod.GET)
    public ModelAndView enableTrain(@PathVariable("id") Long id, Principal principal) {
        String userName = "Guest";
        if (principal != null) {
            userName = principal.getName();
        }
        logger.info(userName + " entering method enableTrain()");

        trainService.enableOrDisableTrainById(id);
        return new ModelAndView("redirect:/train/");
    }

    /**
     * Process a request for set time shift
     * and returns ModelAndView for trains.jsp
     *
     * @param id        enabled/disabled train id.
     * @param principal security principal.
     * @return model and view.
     */
    @RequestMapping(value = "/train/{id}/shift", method = RequestMethod.POST)
    public ModelAndView setShiftByTrainId(@PathVariable("id") Long id,
                                          Principal principal,
                                          Integer shift) {
        String userName = "Guest";
        if (principal != null) {
            userName = principal.getName();
        }
        logger.info(userName + " entering method setShiftByTrainId()");

        trainService.setShiftByTrainId(id, shift);
        return new ModelAndView("redirect:/train/");
    }
}
