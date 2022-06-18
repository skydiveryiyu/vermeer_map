package com.vermeer.map.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class ParkingMetaData {
    private final UUID id;
    private final String name;

    public ParkingMetaData(@JsonProperty("id") UUID id,
                           @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
