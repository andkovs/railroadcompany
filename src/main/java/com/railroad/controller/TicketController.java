package com.railroad.controller;

import com.railroad.core.service.StationService;
import com.railroad.core.service.TicketService;
import com.railroad.core.service.TrainService;
import com.railroad.model.dto.TicketFormDto;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class TicketController {

    private static final Logger logger = Logger.getLogger(MasterController.class);

    private final
    TicketService ticketService;

    private final
    StationService stationService;

    private final
    TrainService trainService;

    @Autowired
    public TicketController(StationService stationService, TicketService ticketService, TrainService trainService) {
        this.stationService = stationService;
        this.ticketService = ticketService;
        this.trainService = trainService;
    }

    /**
     * Process a request and returns ModelAndView for tickets.jsp.
     *
     * @param depStationId departure station id.
     * @param arrStationId arrive station id.
     * @param fromTime     time from.
     * @param toTime       time to.
     * @param principal    security principal.
     * @return model and view.
     */
    @RequestMapping(value = {"/ticket"}, method = RequestMethod.GET)
    public ModelAndView tickets(@RequestParam(value = "selectDepStation", required = false) Long depStationId,
                                @RequestParam(value = "selectArrStation", required = false) Long arrStationId,
                                @RequestParam(value = "fromTime", required = false) String fromTime,
                                @RequestParam(value = "toTime", required = false) String toTime,
                                Principal principal) {
        String userName = "Guest";
        if (principal != null) {
            userName = principal.getName();
        }
        logger.info(userName + " entering method tickets()");

        ModelAndView mav = new ModelAndView("master");
        mav.addObject("title", "Tickets");
        mav.addObject("stations", stationService.getAllStations());
        if (depStationId != null && arrStationId != null && fromTime != null && toTime != null) {
            mav.addObject("searchResult", trainService.getSearchResult(depStationId, arrStationId, fromTime, toTime));
        }
        mav.addObject("userClickTickets", true);
        return mav;
    }

    /**
     * Process a request and returns ModelAndView for ticket.jsp
     *
     * @param principal security principal.
     * @return model and view.
     */
    @RequestMapping(value = "/ticket/{trainId}/{depStationId}/{arrStationId}", method = RequestMethod.GET)
    public ModelAndView getTicket(@PathVariable("trainId") Long trainId,
                                  @PathVariable("depStationId") Long depStationId,
                                  @PathVariable("arrStationId") Long arrStationId,
                                  Principal principal) {
        String userName = "Guest";
        if (principal != null) {
            userName = principal.getName();
        }
        logger.info(userName + " entering method getTicket()");
        ModelAndView mav = new ModelAndView("master");
        mav.addObject("title", "Ticket");
        mav.addObject("ticket", ticketService.getTicketForm(trainId, depStationId, arrStationId));
        mav.addObject("userClickTicket", true);
        return mav;
    }

    /**
     * Process a request for add
     * new ticket
     * and returns ModelAndView for ticket.jsp
     *
     * @param trainId      train id.
     * @param arrStationId arrival station id.
     * @param depStationId departure station id.
     * @param principal    security principal.
     * @param red          redirect attribute.
     * @return model and view.
     */
    @RequestMapping(value = "/ticket/buy", method = RequestMethod.POST)
    public ModelAndView saveTicket(Long trainId,
                                   Long depStationId,
                                   Long arrStationId,
                                   RedirectAttributes red,
                                   Principal principal) {
        String userName = "Guest";
        if (principal != null) {
            userName = principal.getName();
        }
        logger.info(userName + " entering method saveTicket()");

        TicketFormDto ticket = ticketService.getTicketForm(trainId, depStationId, arrStationId);
        String error = ticketService.saveTicket(ticket, principal.getName());
        String success = null;
        if (error == null) {
            success = "Ticket purchase was successful!";
        }
        red.addFlashAttribute("error", error);
        red.addFlashAttribute("success", success);
        return new ModelAndView("redirect:/ticket/" + ticket.getTrain().getTrainId() +
                "/" + ticket.getDepStation().getStationId() +
                "/" + ticket.getArrStation().getStationId());
    }
}
