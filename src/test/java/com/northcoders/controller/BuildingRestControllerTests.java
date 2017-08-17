package com.northcoders.controller;

import com.northcoders.model.Building;
import com.northcoders.repository.BuildingRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(value = BuildingRestController.class)
public class BuildingRestControllerTests {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BuildingRepository buildingRepository;

    @Test
    public void showIndividualBuilding() throws Exception {

        Building building = new Building();
        building.setBuildingId(1L);
        building.setBuildingName("GreenLeaves");
        building.setBuildingType("Bungalow");
        building.setNoOfRooms(10);
        building.setNoOfParkingSpaces(4);

        Mockito.when(buildingRepository.findOne(Mockito.anyLong())).thenReturn(building);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/buildings/1").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{buildingId:1,buildingName:GreenLeaves,buildingType:Bungalow,noOfRooms:10,noOfParkingSpaces:4}";

        System.out.println(result.getResponse().getContentAsString());

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void showAllBuildings() throws Exception {

        Building building1 = new Building();
        building1.setBuildingId(1L);
        building1.setBuildingName("GreenLeaves");
        building1.setBuildingType("Bungalow");
        building1.setNoOfRooms(10);
        building1.setNoOfParkingSpaces(4);

        Building building2 = new Building();
        building2.setBuildingId(2L);
        building2.setBuildingName("CIS");
        building2.setBuildingType("Office");
        building2.setNoOfRooms(100);
        building2.setNoOfParkingSpaces(20);

        List<Building> buildings = new ArrayList<Building>();

        buildings.add(building1);
        buildings.add(building2);

        Mockito.when(buildingRepository.findAll()).thenReturn(buildings);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/buildings").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "[{buildingId:1,buildingName:GreenLeaves,buildingType:Bungalow,noOfRooms:10,noOfParkingSpaces:4},{buildingId:2,buildingName:CIS,buildingType:Office,noOfRooms:100,noOfParkingSpaces:20}]";

        System.out.println(result.getResponse().getContentAsString());

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }


    @Test
    public void insertNewBuilding() throws Exception {

        Building building = new Building();
        building.setBuildingId(1L);
        building.setBuildingName("GreenLeaves");
        building.setBuildingType("Bungalow");
        building.setNoOfRooms(10);
        building.setNoOfParkingSpaces(4);

        String buildingJson = new ObjectMapper().writeValueAsString(building);

        Mockito.when(buildingRepository.save(building)).thenReturn(building);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/buildings/-1").contentType(MediaType.APPLICATION_JSON).content(buildingJson);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        Mockito.verify(buildingRepository, Mockito.times(1)).save(Mockito.any(Building.class));
        Assert.assertEquals(200, result.getResponse().getStatus());
    }

}

