package com.northcoders.controller;

import com.northcoders.model.Building;
import com.northcoders.repository.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BuildingRestController {


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
        Building building = buildingRepository.findOne(buildingId);

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
        System.out.println("Updating Building " + buildingId);

        Building currentBuilding = buildingRepository.findOne(buildingId);

        if (currentBuilding==null) {
            System.out.println("User with id " + buildingId + " not found");
            return new ResponseEntity<Building>(HttpStatus.NOT_FOUND);
        }

        currentBuilding.setBuildingName(building.getBuildingName());
        currentBuilding.setBuildingType(building.getBuildingType());
        currentBuilding.setNoOfRooms(building.getNoOfRooms());
        currentBuilding.setNoOfParkingSpaces(building.getNoOfParkingSpaces());

        buildingRepository.save(currentBuilding);
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


