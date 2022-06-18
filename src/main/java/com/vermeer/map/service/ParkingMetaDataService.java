package com.vermeer.map.service;

import com.vermeer.map.dao.ParkingMetaDataDAO;
import com.vermeer.map.model.ParkingMetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParkingMetaDataService {

    private final ParkingMetaDataDAO parkingMetaDataDAO;

    @Autowired
    public ParkingMetaDataService(@Qualifier("postgres") ParkingMetaDataDAO parkingMetaDataDAO) {
        this.parkingMetaDataDAO = parkingMetaDataDAO;
    }

    public int addParkingMetaData(ParkingMetaData data) {
        return parkingMetaDataDAO.insertParkingMetaData(data);
    }

    public List<ParkingMetaData> getAllParkingMetaData() {
        return parkingMetaDataDAO.getAllParkingMetaData();
    }

    public Optional<ParkingMetaData> getParkingMetaDataById(UUID id) {
        return parkingMetaDataDAO.getParkingMetaDataById(id);
    }

    public int deleteParkingMetaDataById(UUID id) {
        return parkingMetaDataDAO.deleteParkingMetaDataById(id);
    }

    public int updataParkingMetaDataById(UUID id, ParkingMetaData data) {
        return parkingMetaDataDAO.updateParkingMetaDataById(id, data);
    }
}
