package ro.msg.learning.model;

import lombok.Data;

@Data
public class OrderDetail {

    private int orderDetailId;

    private int order; //orderId

    private int product; //productId

    private int quantity;

    public OrderDetail(int orderDetailId, int order, int product, int quantity) {
        this.orderDetailId = orderDetailId;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }
}
