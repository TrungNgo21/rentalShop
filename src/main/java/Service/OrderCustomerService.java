package Service;


import DataAccess.DataAccess;
import Middleware.OrderMiddleware;
import Model.Order.Order;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderCustomerService extends OrderService {
    public OrderCustomerService(DataAccess db, OrderMiddleware checker) {
        super(db, checker);
    }

    @Override
    public String idCreation() {
        return null;
    }

    @Override
    public void add(Order template) {

    }

    @Override
    public void edit(Order template) {

    }

    @Override
    public void delete(Order template) {

    }

    @Override
    public Order getOne(String orderId) {
        if (checker.isBelongedToCurrentUser(orderId, db.getCurrentUser())) {
            for (Order order : db.getAllOrders()) {
                if (order.getOrderId().equals(orderId)) {
                    return order;
                }
            }
        }
        return null;

    }

    @Override
    public HashMap<String, Order> getAll() {
        HashMap<String, Order> orders = new HashMap<>();
        for(Order order : db.getCurrentUser().getRentalList()){
            orders.put(order.getOrderId(), order);
        }
        return orders;
    }

}
