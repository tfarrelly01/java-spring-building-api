package com.northcoders.model;

import javax.persistence.*;

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
    public String getBuildingName() {
        return this.name;
    }

    public void setBuildingName(String name) {
        this.name = name;
    }

    @Column(name="type")
    public String getBuildingType() {
        return this.type;
    }

    public void setBuildingType(String type) {
        this.type = type;
    }

    @Column(name="no_of_rooms")
    public int getNoOfRooms() {
        return this.noOfRooms;
    }

    public void setNoOfRooms(int noOfRooms) {
        this.noOfRooms = noOfRooms;
    }

    @Column(name="no_of_parking_spaces")
    public int getNoOfParkingSpaces() {
        return this.noOfParkingSpaces;
    }

    public void setNoOfParkingSpaces(int noOfParkingSpaces) {
        this.noOfParkingSpaces = noOfParkingSpaces;
    }

}
