package Middleware;

import Model.Order.Order;
import Model.User.User;

public class OrderMiddleware {
    public boolean isBelongedToCurrentUser(String orderId, User currentUser){
        for(Order order : currentUser.getRentalList()){
            if(orderId.equals(order.getOrderId())){
                return true;
            }
        }
        return false;
    }
}
