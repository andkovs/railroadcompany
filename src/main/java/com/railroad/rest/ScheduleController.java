package com.railroad.rest;

import com.railroad.core.service.ScheduleService;
import com.railroad.model.dto.ScheduleAddDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ScheduleController {

    private final
    ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    /**
     * Process a request for add
     * new schedule
     * and returns ModelAndView for train.jsp
     *
     * @return model and view.
     */
    @RequestMapping(value = "/schedule/add", method = RequestMethod.POST)
    public ModelAndView saveSchedule(ScheduleAddDto scheduleAddDto, RedirectAttributes red) {
        scheduleService.saveSchedule(scheduleAddDto);
        red.addFlashAttribute("tabpanel", false);
        return new ModelAndView("redirect:/train/" + scheduleAddDto.getTrainId());
    }

    /**
     * Process a request for remove schedule
     * and returns ModelAndView for station.jsp
     *
     * @param id removed schedule id.
     * @return model and view.
     */
    @RequestMapping(value = "/schedule/{id}/delete", method = RequestMethod.GET)
    public ModelAndView removeSchedule(@PathVariable("id") Long id, RedirectAttributes red) {
        Long trainId = scheduleService.getTrainIdByScheduleId(id);
        scheduleService.removeScheduleById(id);
        red.addFlashAttribute("tabpanel", false);
        return new ModelAndView("redirect:/train/"+trainId);
    }
}
