package com.railroad.rest;

import com.railroad.core.service.WagonService;
import com.railroad.model.dto.WagonAddDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WagonController {

    private final
    WagonService wagonService;

    @Autowired
    public WagonController(WagonService wagonService) {
        this.wagonService = wagonService;
    }

    /**
     * Process a request for remove wagon
     * and returns ModelAndView for station.jsp
     *
     * @param id removed wagon id.
     * @return model and view.
     */
    @RequestMapping(value = "/wagon/{id}/delete", method = RequestMethod.GET)
    public ModelAndView removeWagon(@PathVariable("id") Long id, RedirectAttributes red) {
        Long trainId = wagonService.getTrainIdByWagonId(id);
        wagonService.removeWagonById(id);
        red.addFlashAttribute("tabpanel", true);
        return new ModelAndView("redirect:/train/"+trainId);
    }

    /**
     * Process a request for add
     * new wagon
     * and returns ModelAndView for traimav.addObject("tabpanel", "train");n.jsp
     *
     * @return model and view.
     */
    @RequestMapping(value = "/wagon/add", method = RequestMethod.POST)
    public ModelAndView saveWagon(WagonAddDto wagonAddDto, RedirectAttributes red) {
        wagonService.saveWagon(wagonAddDto);
        ModelAndView mav = new ModelAndView("redirect:/train/" + wagonAddDto.getTrainId());
        red.addFlashAttribute("tabpanel", true);
        return mav;
    }
}
