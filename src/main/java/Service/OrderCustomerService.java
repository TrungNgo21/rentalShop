package Service;


import DataAccess.DataAccess;
import Middleware.OrderMiddleware;
import Model.Order.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class OrderCustomerService extends OrderService {
    public OrderCustomerService(DataAccess db, OrderMiddleware checker) {
        super(db, checker);
    }

    @Override
    public String idCreation() {
        UUID uuid = UUID.randomUUID();
        return "O00" + uuid;
    }

    @Override
    public void add(Order order) {
        DataAccess.getCurrentUser().getRentalList().add(order);
    }

    @Override
    public void edit(Order template) {

    }

    @Override
    public void delete(Order order) {
        DataAccess.getCurrentUser().getRentalList().remove(order);
    }

    @Override
    public Order getOne(String orderId) {
        for(Order order : DataAccess.getCurrentUser().getRentalList()){
            if(order.getOrderId().equals(orderId)){
                return order;
            }
        }
        return null;
//        if (checker.isBelongedToCurrentUser(orderId, DataAccess.getCurrentUser())) {
//            for (Order order : DataAccess.getAllOrders()) {
//                if (order.getOrderId().equals(orderId)) {
//                    return order;
//                }
//            }
//        }
//        return null;

    }

    @Override
    public HashMap<String, Order> getAll() {
        HashMap<String, Order> orders = new HashMap<>();
        for(Order order : DataAccess.getCurrentUser().getRentalList()){
            orders.put(order.getOrderId(), order);
        }
        return orders;
    }

    public Order getCurrentOrder(){
        return DataAccess.getCurrentOrder();
    }

    public void setCurrentOrder(Order currentOrder){
        DataAccess.setCurrentOrder(currentOrder);
    }

}
