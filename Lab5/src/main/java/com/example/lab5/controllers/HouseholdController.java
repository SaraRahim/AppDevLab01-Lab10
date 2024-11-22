package com.example.lab5.controllers;

import ch.qos.logback.core.model.Model;
import com.example.lab5.entities.Household;
import com.example.lab5.services.HouseholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/api/households")
public class HouseholdController {

    @Autowired
    private HouseholdService householdService;

    @GetMapping("/{eircode}")
    public Household getHouseholdByEircode(@PathVariable String eircode) {
        return householdService.findByEircode(eircode);
    }

    @GetMapping("/{eircode}/with-pets")
    public Household getHouseholdByEircodeWithPets(@PathVariable String eircode) {
        return householdService.findByEircodeWithPets(eircode);
    }

    @GetMapping("/no-pets")
    public List<Household> getHouseholdsWithNoPets() {
        return householdService.findHouseholdsWithNoPets();
    }

    @GetMapping("/households")
    public ModelAndView getAllHouseholds() {
        List<Household> households = householdService.getAllHouseholds();
        ModelAndView modelAndView = new ModelAndView("households");
        modelAndView.addObject("households", households);
        return modelAndView;
    }
}