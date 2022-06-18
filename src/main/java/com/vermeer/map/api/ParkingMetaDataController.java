package com.vermeer.map.api;

import com.vermeer.map.model.ParkingMetaData;
import com.vermeer.map.service.ParkingMetaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/parkingmetadata")
@RestController
public class ParkingMetaDataController {
    private final ParkingMetaDataService parkingMetaDataService;

    @Autowired
    public ParkingMetaDataController(ParkingMetaDataService parkingMetaDataService) {
        this.parkingMetaDataService = parkingMetaDataService;
    }

    @PostMapping
    public void addParkingMetaData(@RequestBody ParkingMetaData data) {
        parkingMetaDataService.addParkingMetaData(data);
    }

    @GetMapping
    public List<ParkingMetaData> getAllParkingMetaData() {
        return parkingMetaDataService.getAllParkingMetaData();
    }

    @GetMapping(path = "{id}")
    public ParkingMetaData getParkingMetaData(@PathVariable("id") UUID id) {
        return parkingMetaDataService.getParkingMetaDataById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteParkingMetaData(@PathVariable("id") UUID id) {
        parkingMetaDataService.deleteParkingMetaDataById(id);
    }

    @PutMapping(path = "{id}")
    public void updataParkingMetaData(@PathVariable("id") UUID id, @RequestBody ParkingMetaData data) {
        parkingMetaDataService.updataParkingMetaDataById(id, data);
    }
}
