package Middleware;

import Model.Order.Order;
import Model.Product.Product;
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

    public boolean isOutOfStock(Product product, int orderQ){
        return orderQ <= product.getNumOfCopies();
    }
}
