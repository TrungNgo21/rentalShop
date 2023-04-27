package Service;

import DataAccess.DataAccess;
import Middleware.UserMiddleware;
import Model.Order.Order;
import Model.Product.Product;
import Model.User.User;

import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.Map;

public class AdminService implements Services<User> {
    private final DataAccess db = new DataAccess();
    private final UserMiddleware checker = new UserMiddleware();
    @Override
    public String idCreation() {
        return null;
    }

    @Override
    public void add(User user) {
        if(!checker.isDuplicatedUsername(user.getUserName(), DataAccess.getAllUsers())) {
            DataAccess.getAllUsers().put(idCreation(), user);
        }else{
            throw new Error("Duplicate username!");
        }
    }

    @Override
    public void edit(User user) {
        DataAccess.getAllUsers().put(user.getUserId(), user);
    }

    @Override
    public void delete(User user) {
        DataAccess.getAllUsers().remove(user.getUserId());
    }

    @Override
    public User getOne(String id) { // Search User
        return DataAccess.getAllUsers().get(id);
    }

    @Override
    public HashMap<String, User> getAll() {
//        HashMap <String, User> user = new HashMap<String, User>();
//        for(Map.Entry<String, User> tmp : db.getAllUsers().entrySet()) {
//            user.put(tmp.getKey(), tmp.getValue());
//        }
        return DataAccess.getAllUsers();
    }

}
