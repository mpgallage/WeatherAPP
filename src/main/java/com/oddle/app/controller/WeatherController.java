package com.oddle.app.controller;

import com.oddle.app.model.City;
import com.oddle.app.model.Report;
import com.oddle.app.service.APIService;
import com.oddle.app.service.CityService;
import com.oddle.app.service.ReportService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    @Qualifier(value = "apiService")
    private APIService apiService;

    @Autowired
    @Qualifier(value = "cityService")
    private CityService cityService;

    @Autowired
    @Qualifier(value = "reportService")
    private ReportService reportService;

    @RequestMapping(value = "", method = RequestMethod.GET)
	public String home(ModelMap model) {
		model.addAttribute("city", new City());
		return "search";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(@ModelAttribute("name")String name, ModelMap model) {

        model.addAttribute("city", new City());
        try {
            Report report = apiService.callAPI(name);
            model.addAttribute("report", report);

            City city = new City();
            city.setName(report.getCity().getName());
            city.setCountryCode(report.getCity().getCountryCode());

            cityService.addCity(city);

            reportService.addReport(report);

        } catch (IllegalStateException e) {
            model.addAttribute("error", "City not found");
        } catch (Exception e) {
            model.addAttribute("error", "Cannot make the webservice call at the moment");
        }
		return "search";
	}

    @RequestMapping("/remove/{id}/{cityName}")
    public String removeReport(@PathVariable("id") int id, @PathVariable("cityName") String cityName, RedirectAttributes redirectAttributes){

        this.reportService.removeReport(id);
        redirectAttributes.addFlashAttribute("message", "Weather report deleted!");
        return "redirect:/city/viewHistory/" + cityName;
    }
}
