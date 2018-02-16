package ro.msg.learning.model;

import lombok.Data;

@Data
public class Address {

    private int addressId;

    private String country;

    private String city;

    private String county;

    private String streetAddress;

    public Address(int addressId, String country, String city, String county, String streetAddress) {
        this.addressId = addressId;
        this.country = country;
        this.city = city;
        this.county = county;
        this.streetAddress = streetAddress;
    }

    public Address() {

    }
}
