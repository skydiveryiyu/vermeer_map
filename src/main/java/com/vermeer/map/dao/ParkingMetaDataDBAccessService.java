package com.vermeer.map.dao;

import com.vermeer.map.model.ParkingMetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class ParkingMetaDataDBAccessService implements ParkingMetaDataDAO{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ParkingMetaDataDBAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertParkingMetaData(UUID id, ParkingMetaData data) {
        final String sql = "INSERT INTO parkingmetadata (id, name) VALUES (?, ?)";
        int status = jdbcTemplate.update(
                sql,
                id,
                data.getName());
        if(status != 0){
            System.out.println("ParkingMetaData data [" + data.getName() + "] insert for UUID " + id);
        }else{
            System.out.println("Failed to insert ParkingMetaData [" + id + " : " + data.getName() + "]");
        }
        return status;
    }

    @Override
    public List<ParkingMetaData> getAllParkingMetaData() {
        final String rawSql = "SELECT id, name from parkingmetadata";
        List<ParkingMetaData> list = jdbcTemplate.query(rawSql, (resultSet, index) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new ParkingMetaData(id, name);
        });
        return list;
    }

    @Override
    public Optional<ParkingMetaData> getParkingMetaDataById(UUID id) {
        final String sql = "SELECT id, name FROM parkingmetadata where id = ?";
        ParkingMetaData data = jdbcTemplate.queryForObject(
                    sql,
                    new Object[]{id},
                    (resultSet, index) -> {
                        UUID parkingUuid = UUID.fromString(resultSet.getString("id"));
                        String name = resultSet.getString("name");
                        return new ParkingMetaData(parkingUuid, name);
                    });

        return Optional.ofNullable(data);
    }

    @Override
    public int deleteParkingMetaDataById(UUID id) {
        final String sql = "DELETE FROM parkingmetadata where id = ?";
        int status = jdbcTemplate.update(sql, id);
        if(status != 0){
            System.out.println("ParkingMetaData data deleted for UUID " + id);
        }else{
            System.out.println("No ParkingMetaData found with UUID " + id);
        }
        return status;
    }

    @Override
    public int updateParkingMetaDataById(UUID id, ParkingMetaData data) {
        final  String sql = "UPDATE parkingmetadata SET name = ? WHERE id = ?";
        int status = jdbcTemplate.update(sql, data.getName(), id);
        if(status != 0){
            System.out.println("ParkingMetaData data updated [" + data.getName() + "] for UUID " + id);
        }else{
            System.out.println("Failed to update ParkingMetaData  [" + data.getName() + "] with UUID " + id);
        }
        return status;
    }
}
