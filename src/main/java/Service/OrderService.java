package Service;

import DataAccess.DataAccess;
import Middleware.OrderMiddleware;
import Model.Order.Order;

public abstract class OrderService implements Services<Order> {
    protected DataAccess db;
    protected OrderMiddleware checker;

    public OrderService(DataAccess db, OrderMiddleware checker) {
        this.db = db;
        this.checker = checker;
    }

    public OrderService(DataAccess db) {
        this.db = db;
    }
}
