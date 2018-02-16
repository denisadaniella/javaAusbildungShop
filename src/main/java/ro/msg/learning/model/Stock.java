package ro.msg.learning.model;

import lombok.Data;

@Data
public class Stock {

    private int stockId;

    private int product; //productID

    private int location; //locationId

    private int quantity;

    public Stock(int stockId, int product, int location, int quantity) {
        this.stockId = stockId;
        this.product = product;
        this.location = location;
        this.quantity = quantity;
    }
}
