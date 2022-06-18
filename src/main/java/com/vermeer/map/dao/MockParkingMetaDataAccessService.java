package com.vermeer.map.dao;

import com.vermeer.map.model.ParkingMetaData;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("mockParkingMetaData")
public class MockParkingMetaDataAccessService implements ParkingMetaDataDAO{
    private static List<ParkingMetaData> DB = new ArrayList<>();

    @Override
    public int insertParkingMetaData(UUID id, ParkingMetaData data) {
        DB.add(new ParkingMetaData(id, data.getName()));
        return 1;
    }

    @Override
    public List<ParkingMetaData> getAllParkingMetaData() {
        return DB;
    }

    @Override
    public Optional<ParkingMetaData> getParkingMetaDataById(UUID id) {
        return DB.stream()
                .filter(ParkingMetaData -> ParkingMetaData.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deleteParkingMetaDataById(UUID id) {
        Optional<ParkingMetaData> foundParkingMetaData = getParkingMetaDataById(id);
        if (foundParkingMetaData.isEmpty()) {
            return 0;
        }
        DB.remove(foundParkingMetaData.get());
        return 1;
    }

    @Override
    public int updateParkingMetaDataById(UUID id, ParkingMetaData data) {
        return getParkingMetaDataById(id)
                .map(parking -> {
                    int indexOfParkingToDelete = DB.indexOf(data);
                    if (indexOfParkingToDelete >= 0) {
                        DB.set(indexOfParkingToDelete, new ParkingMetaData(id, data.getName()));
                        return 1;
                    }
                    return 0;
                })
                .orElse( 0);
    }
}
