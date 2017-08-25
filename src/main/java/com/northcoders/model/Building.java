package com.northcoders.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
public class Building {

    private Long buildingId = null;
    private String name = null;
    private String type = null;
    private int noOfRooms;
    private int noOfParkingSpaces;
/*
    public Building(String name, String type, int noOfRooms, int noOfParkingSpaces) {
        this.name = name;
        this.type = type;
        this.noOfRooms = noOfRooms;
        this.noOfParkingSpaces = noOfParkingSpaces;
    }
*/
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="building_id")
    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    @Column(name="name")
    @NotEmpty(message = "The name of the building is required")
    public String getBuildingName() {
        return this.name;
    }

    public void setBuildingName(String name) {
        this.name = name;
    }

    @Column(name="type")
    @NotEmpty(message = "The type of building is required")
    public String getBuildingType() {
        return this.type;
    }

    public void setBuildingType(String type) {
        this.type = type;
    }

    @Column(name="no_of_rooms")
    @Min(value = 1, message = "The number of rooms should be at least 1")
    public int getNoOfRooms() {
        return this.noOfRooms;
    }

    public void setNoOfRooms(int noOfRooms) {
        this.noOfRooms = noOfRooms;
    }

    @Column(name="no_of_parking_spaces")
    @Min(value = 0, message = "The number of parking spaces should be at least 0")
    public int getNoOfParkingSpaces() {
        return this.noOfParkingSpaces;
    }

    public void setNoOfParkingSpaces(int noOfParkingSpaces) {
        this.noOfParkingSpaces = noOfParkingSpaces;
    }

    // Used for LOGGING
    @Override
    public String toString() {
        return "Building{" +
                "buildingId=" + buildingId +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", noOfRooms='" + noOfRooms + '\'' +
                ", noOfParkingSpaces=" + noOfParkingSpaces +
                '}';
    }

}
