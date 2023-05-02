import DataAccess.DataAccess;
import Service.AdminService;

public class minh {
    public static void main(String[] args) {
        AdminService admin = new AdminService();
        DataAccess.loadAllData();
//        System.out.println(admin.filterAccountType("VIPAccount"));
        System.out.println(admin.getOne("C003"));
    }
}
