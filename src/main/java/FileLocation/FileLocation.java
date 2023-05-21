package FileLocation;

public class FileLocation {
    private final String userFileDir = "src/main/java/DataFile/User.csv";

    private final String workingDir = System.getProperty("user.dir");

    private final String revenueDir = "src/main/java/DataFile/StoreRevenue.csv";
    private final String imageDir = "src/main/resources/com/example/officialjavafxproj/Image/";

    private final String AdminFileDir = "src/main/java/DataFile/Admin.csv";

    private final String AccountFileDir = "src/main/java/DataFile/Account.csv";
    private final String ProductFileDir = "src/main/java/DataFile/Product.csv";
    private final String OrderFileDir = "src/main/java/DataFile/Order.csv";
    private final String OrderDetailFileDir = "src/main/java/DataFile/OrderDetail.csv";
    private final String CartFileDir = "src/main/java/DataFile/Cart.csv";

    public String getUserFileDir() {
        return userFileDir;
    }

    public String getAccountFileDir() {
        return AccountFileDir;
    }

    public String getProductFileDir() {
        return ProductFileDir;
    }

    public String getOrderFileDir() {
        return OrderFileDir;
    }

    public String getOrderDetailFileDir() {
        return OrderDetailFileDir;
    }

    public String getCartFileDir() {
        return CartFileDir;
    }

    public String getAdminFileDir() {
        return AdminFileDir;
    }

    public String getWorkingDir() {
        return workingDir;
    }

    public String getRevenueDir() {
        return revenueDir;
    }

    public String getImageDir() {
        return workingDir + "/" + imageDir;
    }
}
