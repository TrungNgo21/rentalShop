package Model.Order;

import java.time.LocalDate;
import java.util.ArrayList;

public class Order {
    private String orderId;
    private String userId;
    private ArrayList<OrderDetail> orders = new ArrayList<>();

    private LocalDate orderDate;

    private double totalPrice;

    public Order(String orderId, String userId, LocalDate date, double totalPrice) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderDate = date;
        this.totalPrice = totalPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getUserId() {
        return userId;
    }

    public LocalDate getOrderDate(){
        return orderDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public ArrayList<OrderDetail> getOrders() {
        return orders;
    }

    public void addOrderDetailsToOrder(OrderDetail detail){
        orders.add(detail);
    }
}
