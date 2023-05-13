import DataAccess.DataAccess;
import FileLocation.FileLocation;
import Model.User.User;
import Service.AdminService;

import java.util.HashMap;
import java.util.Map;

public class minh {
    public static void main(String[] args) {
        FileLocation fileLocation = new FileLocation();
        System.out.println(fileLocation.getImageDir());
    }
}
