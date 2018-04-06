package com.railroad.controller.rest;

import com.railroad.core.service.TrainService;
import com.railroad.model.dto.TicketDto;
import com.railroad.model.dto.TrainDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TicketRestController {

    private final
    TrainService trainService;

    @Autowired
    public TicketRestController(TrainService trainService) {
        this.trainService = trainService;
    }

    /**
     * Process a request and returns list of ticket dto's with json format.
     *
     * @param id train id.
     * @return list of ticket dto's with json format.
     */
    @GetMapping("/rest/ticket/train/{id}")
    public List<TicketDto> getTicketsByTrainId(@PathVariable("id") Long id) {
        TrainDto trainDto = trainService.getTrainById(id);
        return trainDto.getTickets();
    }
}
