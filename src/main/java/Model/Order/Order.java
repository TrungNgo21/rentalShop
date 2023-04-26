package Model.Order;

import java.util.ArrayList;

public class Order {
    private String orderId;
    private String userId;
    private ArrayList<OrderDetail> orders;

    public Order(String orderId, String userId) {
        this.orderId = orderId;
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getUserId() {
        return userId;
    }

    public ArrayList<OrderDetail> getOrders() {
        return orders;
    }

    public void addOrderDetailsToOrder(OrderDetail detail){
        orders.add(detail);
    }
}
