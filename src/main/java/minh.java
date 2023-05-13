import DataAccess.DataAccess;
import Model.User.User;
import Service.AdminService;

import java.util.HashMap;
import java.util.Map;

public class minh {
    public static void main(String[] args) {
        AdminService admin = new AdminService();
        DataAccess.loadAllData();
//        System.out.println(admin.getOne("C003"));
//        for(Map.Entry<String, User> m : admin.filterAccountType("GuestAccount").entrySet()) {
//            System.out.println(m.getKey());
//        }
        String user = "trungxiro1234";
        String accountType = "GuestAccount";
        User displayUser = admin.getOne(user);
        HashMap<String,User> sortedUsers = new HashMap<String, User>();
//        if(displayUser == null && !admin.filterAccountType(accountType).isEmpty()) {
//
//            for(Map.Entry<String, User> tmp : admin.filterAccountType(accountType).entrySet()) {
//                System.out.println(tmp.getValue().getUserId() + " " + tmp.getValue().getAccount().getAccountType());
//            }
//        }
//        else if(displayUser != null && admin.filterAccountType(accountType).isEmpty()) {
//            System.out.println(displayUser.getUserId() + " " + displayUser.getAccount().getAccountType());
//        }
//        else if(displayUser != null && !admin.filterAccountType(accountType).isEmpty()) {
//            if(displayUser.getAccount().getAccountType().equals(accountType)) {
//                System.out.println(displayUser.getUserId() +  " " + displayUser.getAccount().getAccountType());
//            }
//            else {
//                System.out.println("THere is no user");
//            }
//        }
        HashMap<String, User> temp =  new HashMap<String, User>();
        temp = admin.filterAccountType(accountType);
        for(Map.Entry<String, User> tmp : temp.entrySet()) {
                System.out.println(tmp.getValue().getUserId() + " " + tmp.getValue().getAccount().getAccountType());
            }
        System.out.println("After sorting: ");
//        temp = admin.sortFromAToZ(accountType);
//        for(Map.Entry<String, User> tmp : temp.entrySet()) {
//            System.out.println(tmp.getValue().getUserId() + " " + tmp.getValue().getAccount().getAccountType());
//        }
    }
}
