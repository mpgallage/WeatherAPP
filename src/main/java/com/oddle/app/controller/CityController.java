package com.oddle.app.controller;

import com.oddle.app.model.City;
import com.oddle.app.model.Report;
import com.oddle.app.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Set;

/**
 * @author : maal (Malaka Gallage)
 * @since : 6/12/17
 */
@Controller
@RequestMapping("/city")
public class CityController {

    @Autowired(required=true)
    @Qualifier(value="cityService")
    private CityService cityService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String listCities(Model model) {
        model.addAttribute("city", new City());
        model.addAttribute("listCities", this.cityService.listCities());
        return "city";
    }

    //For add and update city both
    @RequestMapping(value= "/add", method = RequestMethod.POST)
    public String addCity(@ModelAttribute("city") City city, RedirectAttributes redirectAttributes){

        if(city.getId() == 0){
            //new city, add it
            boolean added = this.cityService.addCity(city);
            if (added) {
                redirectAttributes.addFlashAttribute("message", "New city is added successfully!");
            } else {
                redirectAttributes.addFlashAttribute("error", "City already exists!");
            }

        }else{
            //existing city, call update
            this.cityService.updateCity(city);
            redirectAttributes.addFlashAttribute("message", "City is updated successfully");
        }

        return "redirect:/city";

    }

    @RequestMapping("/remove/{id}")
    public String removeCity(@PathVariable("id") int id, RedirectAttributes redirectAttributes){

        this.cityService.removeCity(id);
        redirectAttributes.addFlashAttribute("message", "City is deleted successfully!");
        return "redirect:/city";
    }

    @RequestMapping("/edit/{name}")
    public String editCity(@PathVariable("name") String name, ModelMap model){
        model.addAttribute("city", this.cityService.getCityByName(name));
        model.addAttribute("listCities", this.cityService.listCities());
        return "city";
    }

    @RequestMapping("/viewHistory/{name}")
    public String viewHistory(@PathVariable("name") String name, RedirectAttributes redirectAttributes, ModelMap model){

        City city = this.cityService.getCityByName(name);
        Set<Report> reports = city.getReports();

        if (reports != null && reports.size() > 0) {
            model.addAttribute("reports", reports);
            return "history";
        }

        redirectAttributes.addFlashAttribute("error", "No old reports exists for " + name);
        redirectAttributes.addFlashAttribute("name", name);

        return "redirect:/city";
    }

}
