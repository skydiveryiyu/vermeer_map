package com.vermeer.map.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.vermeer.map.model.ParkingMetaData;

public interface ParkingMetaDataDAO {

    int insertParkingMetaData(UUID id, ParkingMetaData data);

    default int insertParkingMetaData(ParkingMetaData data) {
        UUID id = UUID.randomUUID();
        return insertParkingMetaData(id, data);
    }

    List<ParkingMetaData> getAllParkingMetaData();

    Optional<ParkingMetaData> getParkingMetaDataById(UUID id);

    int deleteParkingMetaDataById(UUID id);

    int updateParkingMetaDataById(UUID id, ParkingMetaData data);
}
