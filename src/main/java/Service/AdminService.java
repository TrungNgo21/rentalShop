package Service;

import DataAccess.DataAccess;
import Middleware.UserMiddleware;
import Model.Order.Order;
import Model.User.User;

import java.util.HashMap;

public class AdminService implements Services<User>{
    private final DataAccess db = new DataAccess();

    @Override
    public String idCreation() {
        return null;
    }

    @Override
    public void add(User template) {

    }

    @Override
    public void edit(User template) {

    }

    @Override
    public void delete(User template) {

    }

    @Override
    public User getOne(String id) {
        return null;
    }

    @Override
    public HashMap<String, User> getAll() {
        return null;
    }
}
