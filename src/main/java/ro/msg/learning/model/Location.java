package ro.msg.learning.model;

import lombok.Data;

@Data
public class Location {

    private int locationId;

    private String name;

    private int address; //addressId

    public Location(int locationId, String name, int address) {
        this.locationId = locationId;
        this.name = name;
        this.address = address;
    }
}
