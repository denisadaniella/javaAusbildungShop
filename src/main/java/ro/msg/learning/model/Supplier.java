package ro.msg.learning.model;

import lombok.Data;

@Data
public class Supplier {

    private int supplierId;

    private String name;

    public Supplier(int supplierId, String name) {
        this.supplierId = supplierId;
        this.name = name;
    }

    public Supplier() {

    }
}
