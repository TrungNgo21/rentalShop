package Service;

import DataAccess.DataAccess;
import Middleware.OrderMiddleware;
import Model.Order.Order;
import Model.User.User;

import java.util.*;

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

    public ArrayList<Order> getSortedOrderID() {
        ArrayList<Order> orderList = DataAccess.getAllOrders();
        Collections.sort(orderList, new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return (o1.getOrderId().compareTo(o2.getOrderId()));
            }
        });
        return orderList;
    }

    public ArrayList<Order> getSortedOrderDate() {
        ArrayList<Order> orderList = DataAccess.getAllOrders();
        Collections.sort(orderList, new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return (o1.getOrderDate().compareTo(o2.getOrderDate()));
            }
        });
        return orderList;
    }

    public ArrayList<Order> getSortedUserID() {
        ArrayList<Order> orderList = DataAccess.getAllOrders();
        Collections.sort(orderList, new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return (o1.getUserId().compareTo(o2.getUserId()));
            }
        });
        return orderList;
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
