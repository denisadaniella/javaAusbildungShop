package ro.msg.learning.model;

import lombok.Data;

@Data
public class Order {

    private int orderId;

    private int shippedFrom; // locationId

    private int customer; //customerId

    private int address; //addressId

    public Order(int orderId, int shippedFrom, int customer, int address) {
        this.orderId = orderId;
        this.shippedFrom = shippedFrom;
        this.customer = customer;
        this.address = address;
    }

    public Order() {

    }

}
