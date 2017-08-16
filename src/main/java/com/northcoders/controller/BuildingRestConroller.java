package com.northcoders.controller;

import com.northcoders.model.Building;
import com.northcoders.repository.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BuildingRestConroller {


    @Autowired
    protected BuildingRepository buildingRepository;

    @CrossOrigin("*")
    @RequestMapping(value="/buildings", method = RequestMethod.GET)
    public List<Building> getAllBuildings() {
        return (List<Building>)buildingRepository.findAll();
    }

    @CrossOrigin("*")
    @RequestMapping(value="/buildings/{buildingId}", method = RequestMethod.GET)
    public Building getIndividualBuilding(@PathVariable Long buildingId) {
        Building building = buildingRepository.findOne(buildingId);

        return building;
    }

    @CrossOrigin("*")
    @RequestMapping(value="/buildings/{buildingId}", method = RequestMethod.POST)
    public void saveBuilding(@RequestBody Building building) {
        buildingRepository.save(building);
    }

}


