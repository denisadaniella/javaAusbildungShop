package ro.msg.learning.model;

import lombok.Data;

@Data
public class Customer {

    private int customerId;

    private String firstName;

    private String lastName;

    private String username;

    public Customer(int customerId, String firstName, String lastName, String username) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
    }

    public Customer() {

    }
}
