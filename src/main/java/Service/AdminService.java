package Service;

import DataAccess.DataAccess;
import Middleware.UserMiddleware;
import Model.Account.Account;
import Model.Order.Order;
import Model.Product.Product;
import Model.User.User;

import javax.xml.crypto.Data;
import java.util.*;

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
        for(Map.Entry<String, User> entry : DataAccess.getSortedUsers().entrySet()) {
            if(id.equals(entry.getValue().getUserId()) || id.equals(entry.getValue().getUserName())) {
                return entry.getValue();
            }
        }
        return null;
    }

    public HashMap<String, User> sortById(String type) {
        HashMap <String, User> sortedByType =  filterAccountType(type);
        List<Map.Entry<String,User> > list = new LinkedList<Map.Entry<String,User> >(sortedByType.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, User>>() {
            @Override
            public int compare(Map.Entry<String, User> o1, Map.Entry<String, User> o2) {
                return (o1.getValue().getUserId().compareTo(o2.getValue().getUserId()));
            }
        });
        HashMap<String,User> temp = new LinkedHashMap<String,User>();
        for (Map.Entry<String,User> user : list){
            temp.put(user.getKey(),user.getValue());
        }
        return temp;
    }
    @Override
    public HashMap<String, User> getAll() {
//        HashMap <String, User> user = new HashMap<String, User>();
//        for(Map.Entry<String, User> tmp : db.getAllUsers().entrySet()) {
//            user.put(tmp.getKey(), tmp.getValue());
//        }
        return DataAccess.getAllUsers();
    }
    // Filter the type of account users
    public HashMap<String, User> filterAccountType(String accountType) { // Display this hashmap to UI
        DataAccess.getSortedUsers().clear();
        for(Map.Entry<String, Account> entry : DataAccess.getAllAccounts().entrySet()) {
            if(entry.getValue().getAccountType().equals(accountType)) {
                DataAccess.getSortedUsers().put(entry.getKey(), entry.getValue().getOwner());
            }
        }
        return DataAccess.getSortedUsers();
    }

    public void sortDecreasingOrderId() {
//        DataAccess.getSortedUsers().clear();
//        HashMap<String, User> sortedUSer = filterAccountType(type);
//        List<Map.Entry<String,User> > list = new LinkedList<Map.Entry<String,User> >(sortedUSer.entrySet());
//        Collections.sort(list, new Comparator<Map.Entry<String, User>>() {
//            @Override
//            public int compare(Map.Entry<String, User> o1, Map.Entry<String, User> o2) {
//                return (o2.getValue().getUserName().compareTo(o1.getValue().getUserName()));
//            }
//        });
//        HashMap<String,User> temp = new LinkedHashMap<String,User>();
//        for (Map.Entry<String,User> user : list){
//            temp.put(user.getKey(),user.getValue());
//        }
//        return temp;
        List<Map.Entry<String, User>> list = new LinkedList<Map.Entry<String, User>>(DataAccess.getSortedUsers().entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, User>>() {
            @Override
            public int compare(Map.Entry<String, User> o1, Map.Entry<String, User> o2) {
                return (o2.getValue().getUserId().compareTo(o1.getValue().getUserId()));
            }
        });
        HashMap<String, User> temp = new LinkedHashMap<String, User>();
        DataAccess.getSortedUsers().clear();
        for (Map.Entry<String, User> user : list) {
            temp.put(user.getKey(), user.getValue());
        }
        DataAccess.getSortedUsers().clear();
        DataAccess.setSortedUsers(temp);
    }

    public void sortIncreasingOrderId() {
//        DataAccess.getSortedUsers().clear();
//        HashMap<String, User> sortedUSer = filterAccountType(type);
//        List<Map.Entry<String,User> > list = new LinkedList<Map.Entry<String,User> >(sortedUSer.entrySet());
//        Collections.sort(list, new Comparator<Map.Entry<String, User>>() {
//            @Override
//            public int compare(Map.Entry<String, User> o1, Map.Entry<String, User> o2) {
//                return (o1.getValue().getUserName().compareTo(o2.getValue().getUserName()));
//            }
//        });
//        HashMap<String,User> temp = new LinkedHashMap<String,User>();
//        for (Map.Entry<String,User> user : list){
//            temp.put(user.getKey(),user.getValue());
//        }
//        return temp;
        List<Map.Entry<String, User>> list = new LinkedList<Map.Entry<String, User>>(DataAccess.getSortedUsers().entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, User>>() {
            @Override
            public int compare(Map.Entry<String, User> o1, Map.Entry<String, User> o2) {
                return (o1.getValue().getUserId().compareTo(o2.getValue().getUserId()));
            }
        });
        HashMap<String, User> temp = new LinkedHashMap<String, User>();
        DataAccess.getSortedUsers().clear();
        for (Map.Entry<String, User> user : list) {
            temp.put(user.getKey(), user.getValue());
        }
        DataAccess.getSortedUsers().clear();
        DataAccess.setSortedUsers(temp);
    }
    public  User getSelectedUser(){
        return DataAccess.getSelectedCustomer();
    }

    public static void setSelectedUser(User user) {
        DataAccess.setSelectedCustomer(user);
    }

    public HashMap<String, User> getSortedCustomer() {
        return DataAccess.getSortedUsers();
    }

}
