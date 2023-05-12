package Service;

import DataAccess.DataAccess;
import Middleware.OrderMiddleware;
import Model.Order.Order;

import java.util.HashMap;

public class OrderAdminService extends OrderService{
    private static Order selectedOrder;
    public OrderAdminService(DataAccess db) {
        super(db);
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
    public Order getOne(String id) {
        for(Order order : db.getAllOrders()){
            if(order.getOrderId().equals(id)){
                return order;
            }
        }
        return null;
    }

    @Override
    public HashMap<String, Order> getAll() {
        HashMap<String, Order> orders = new HashMap<>();
        for(Order order : db.getAllOrders()){
            orders.put(order.getOrderId(), order);
        }
        return orders;
    }

    public Order getSelectedOrder() {return selectedOrder;}

    public void setSelectedOrder(Order order) {
        this.selectedOrder = order;
    }
}
