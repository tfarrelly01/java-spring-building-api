package com.northcoders.controller;

import com.northcoders.model.Building;
//LOGGING
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.northcoders.repository.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BuildingRestController {

    // LOGGING
    private final Logger logger = LoggerFactory.getLogger(BuildingRestController.class);

    @Autowired
    protected BuildingRepository buildingRepository;

    // GET - All Buildings
    @CrossOrigin("*")
    @RequestMapping(value = "/buildings", method = RequestMethod.GET)
    public List<Building> getAllBuildings() {
        return (List<Building>) buildingRepository.findAll();
    }

    // GET - Individual Building by Id
    @CrossOrigin("*")
    @RequestMapping(value = "/buildings/{buildingId}", method = RequestMethod.GET)
    public Building getIndividualBuilding(@PathVariable Long buildingId) {

        // LOGGING
        logger.debug("getIndividualBuilding STARTED");
        Building building = buildingRepository.findOne(buildingId);

        // LOGGING
        if(building == null) {
            logger.warn("building {} doesn't exist", buildingId);
        }

        // LOGGING
        logger.debug("getIndividualBuilding ENDED");

        return building;
    }

    // POST - Create a new Building
    @CrossOrigin("*")
    @RequestMapping(value = "/buildings/{buildingId}", method = RequestMethod.POST)
    public void saveBuilding(@Valid @RequestBody Building building) {
        buildingRepository.save(building);
    }

    // PUT - Update a Building
    @CrossOrigin("*")
    @RequestMapping(value = "/buildings/{buildingId}", method = RequestMethod.PUT)
    public ResponseEntity<Building> updateBuilding(@PathVariable("buildingId") long buildingId, @RequestBody Building building) {

        // LOGGING
        // System.out.println("Updating Building " + buildingId);
        logger.debug("updateBuilding STARTED");

        Building currentBuilding = buildingRepository.findOne(buildingId);

        if (currentBuilding==null) {
    //        System.out.println("Building with id " + buildingId + " not found");
            logger.warn("Building with id {} not found", buildingId);
            return new ResponseEntity<Building>(HttpStatus.NOT_FOUND);
        }

        currentBuilding.setBuildingName(building.getBuildingName());
        currentBuilding.setBuildingType(building.getBuildingType());
        currentBuilding.setNoOfRooms(building.getNoOfRooms());
        currentBuilding.setNoOfParkingSpaces(building.getNoOfParkingSpaces());


        try {
            buildingRepository.save(currentBuilding);
        }
        catch(Exception exc) {
            logger.error("Writing to database:  update building Id {}", exc);
            throw exc;
        }

        // LOGGING
        logger.debug("updateBuilding ENDED");

        return new ResponseEntity<Building>(currentBuilding, HttpStatus.OK);
    }

    // DELETE - Building by Id
    @CrossOrigin("*")
    @RequestMapping(value = "/buildings/{buildingId}", method = RequestMethod.DELETE)
    public ResponseEntity<Building> deleteBuilding(@PathVariable("buildingId") long buildingId) {
        System.out.println("Fetching & Deleting Building with id " + buildingId);

        Building building = buildingRepository.findOne(buildingId);
        if (building == null) {
            System.out.println("Unable to delete. Building with id " + buildingId + " not found");
            return new ResponseEntity<Building>(HttpStatus.NOT_FOUND);
        }

        buildingRepository.delete(buildingId);
        return new ResponseEntity<Building>(HttpStatus.ACCEPTED);
    }

    // DELETE - All buildings
    @CrossOrigin("*")
    @RequestMapping(value = "/buildings/", method = RequestMethod.DELETE)
    public ResponseEntity<Building> deleteAllBuildings() {
        System.out.println("Deleting All Buildings");

        buildingRepository.deleteAll();
        return new ResponseEntity<Building>(HttpStatus.ACCEPTED);
    }
}


