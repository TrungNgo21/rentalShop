package Service;

import DataAccess.DataAccess;
import Middleware.OrderMiddleware;
import Model.Order.Order;
import Model.Order.OrderDetail;
import Model.User.User;

import java.lang.reflect.Array;
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

    public HashMap<String, Order> getSortedOrderID() {
        List<Map.Entry<String, Order>> list = new LinkedList<Map.Entry<String, Order>>(getAll().entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Order>>() {
            @Override
            public int compare(Map.Entry<String, Order> o1, Map.Entry<String, Order> o2) {
                return o1.getValue().getOrderId().compareTo(o2.getValue().getOrderId());
            }
        });
        HashMap<String, Order> temp = new LinkedHashMap<String, Order>();
        for (Map.Entry<String, Order> order : list) {
            temp.put(order.getKey(), order.getValue());
        }
        return temp;
    }

    public HashMap<String, Order> getSortedOrderDate() {
        List<Map.Entry<String, Order>> list = new LinkedList<Map.Entry<String, Order>>(getAll().entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Order>>() {
            @Override
            public int compare(Map.Entry<String, Order> o1, Map.Entry<String, Order> o2) {
                return o1.getValue().getOrderDate().compareTo(o2.getValue().getOrderDate());
            }
        });
        HashMap<String, Order> temp = new LinkedHashMap<String, Order>();
        for (Map.Entry<String, Order> order : list) {
            temp.put(order.getKey(), order.getValue());
        }
        return temp;
    }

    public HashMap<String, Order> getSortedUserID() {
        List<Map.Entry<String, Order>> list = new LinkedList<Map.Entry<String, Order>>(getAll().entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Order>>() {
            @Override
            public int compare(Map.Entry<String, Order> o1, Map.Entry<String, Order> o2) {
                return o1.getValue().getUserId().compareTo(o2.getValue().getUserId());
            }
        });
        HashMap<String, Order> temp = new LinkedHashMap<String, Order>();
        for (Map.Entry<String, Order> order : list) {
            temp.put(order.getKey(), order.getValue());
        }
        return temp;
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

    public HashMap<String, OrderDetail> getAllOrderDetail(Order order) {
        HashMap<String, OrderDetail> orderDetailHashMap = new HashMap<>();
        for(OrderDetail orderDetail: order.getOrders()) {
            orderDetailHashMap.put(orderDetail.getOrderDetailId(), orderDetail);
        }
        return orderDetailHashMap;
    }

    public Order getSelectedOrder() {return selectedOrder;}

    public void setSelectedOrder(Order order) {
        this.selectedOrder = order;
    }
}
