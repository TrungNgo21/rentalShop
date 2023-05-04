package DataAccess;

import Middleware.DateMiddleware;
import Model.Account.Account;
import Model.Account.GuestAccount;
import Model.Account.RegularAccount;
import Model.Account.VIPAccount;
import Model.Order.Cart;
import Model.Order.Order;
import Model.Order.OrderDetail;
import Model.Product.DVD;
import Model.Product.Game;
import Model.Product.MRecords;
import Model.Product.Product;
import Model.User.Admin;
import Model.User.Customer;
import Model.User.User;
import FileLocation.FileLocation;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DataAccess {
    private static final HashMap<String, User> users = new HashMap<>();

    private static final HashMap<String, User> sortedUsers = new HashMap<>();

    private static final HashMap<String, Product> sortedProducts = new HashMap<>();
    private static final HashMap<String, Account> accounts = new HashMap<>();

    private static final ArrayList<Cart> carts = new ArrayList<>();
    private static final HashMap<String, Product> products = new HashMap<>();

    private static final ArrayList<OrderDetail> orderDetails = new ArrayList<>();

    private static final ArrayList<Order> orders = new ArrayList<>();

    private static User currentUser;

    private static Product chosenProduct;

    private static Order currentOrder;

    private static ArrayList<String[]> getDataFromFile(String fileLocation) {
        try {
            ArrayList<String[]> dataFile = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(fileLocation));
            String line = reader.readLine();
            while (line != null) {
                String[] dataLine = line.split(";");
                dataFile.add(dataLine);
                // Do something with the values array
                line = reader.readLine();
            }
            reader.close();
            return dataFile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static void loadAllUsersNoAccounts() {
        ArrayList<String[]> dataFile = getDataFromFile(new FileLocation().getUserFileDir());
        for (String[] userData : Objects.requireNonNull(dataFile)) {
            if (userData[0].equals("ADMIN")) {
                users.put(userData[0],
                        new Admin(userData[0], userData[1], userData[2], userData[3], userData[4], userData[5], userData[6]));
            }
            users.put(userData[0], new Customer(userData[0], userData[1], userData[2], userData[3], userData[4], userData[5], Double.parseDouble(userData[6]), new GuestAccount(), new Cart(), userData[7]));
        }
    }

    private static void loadAllAccounts() {
        ArrayList<String[]> dataFile = getDataFromFile(new FileLocation().getAccountFileDir());
        for (String[] accountData : Objects.requireNonNull(dataFile)) {
            if (Objects.equals(accountData[1], "GuestAccount")) {
                users.get(accountData[7]).setAccount(new GuestAccount(accountData[0], accountData[1], Integer.parseInt(accountData[2]), Integer.parseInt(accountData[3]), Boolean.parseBoolean(accountData[4]), Integer.parseInt(accountData[5]), Boolean.parseBoolean(accountData[6])));
                GuestAccount guestAcc = (GuestAccount) users.get(accountData[7]).getAccount();
                guestAcc.setOwner(users.get(accountData[7]));
                accounts.put(accountData[0], guestAcc);
            } else if (Objects.equals(accountData[1], "RegularAccount")) {
                users.get(accountData[7]).setAccount(new RegularAccount(accountData[0], accountData[1], Integer.parseInt(accountData[2]), Integer.parseInt(accountData[3]), Boolean.parseBoolean(accountData[4]), Integer.parseInt(accountData[5]), Boolean.parseBoolean(accountData[6])));
                RegularAccount regularAcc = (RegularAccount) users.get(accountData[7]).getAccount();
                regularAcc.setOwner(users.get(accountData[7]));
                accounts.put(accountData[0], regularAcc);
            } else {
                users.get(accountData[7]).setAccount(new VIPAccount(accountData[0], accountData[1], Integer.parseInt(accountData[2]), Integer.parseInt(accountData[3]), Boolean.parseBoolean(accountData[4]), Integer.parseInt(accountData[5]), Boolean.parseBoolean(accountData[6])));
                VIPAccount VIPAcc = (VIPAccount) users.get(accountData[7]).getAccount();
                VIPAcc.setOwner(users.get(accountData[7]));
                accounts.put(accountData[0], VIPAcc);
            }
        }
    }

    private static void loadAllProducts() {
        ArrayList<String[]> dataFile = getDataFromFile(new FileLocation().getProductFileDir());
        for (String[] productData : Objects.requireNonNull(dataFile)) {
            if (productData[2].equals("DVD")) {
                DVD dvd = new DVD(productData[0], productData[1], productData[2], productData[3], productData[4], Integer.parseInt(productData[5]), Double.parseDouble(productData[6]), productData[7], productData[8], productData[9]);
                products.put(productData[0], dvd);
            } else if (productData[2].equals("RECORD")) {
                MRecords record = new MRecords(productData[0], productData[1], productData[2], productData[3], productData[4], Integer.parseInt(productData[5]), Double.parseDouble(productData[6]), productData[7], productData[8], productData[9]);
                products.put(productData[0], record);
            } else {
                Game game = new Game(productData[0], productData[1], productData[2], productData[3], productData[4], Integer.parseInt(productData[5]), Double.parseDouble(productData[6]), productData[7], productData[8], productData[9]);
                products.put(productData[0], game);
            }
        }
    }

    private static void loadAllOrderDetails() {
        ArrayList<String[]> dataFile = getDataFromFile(new FileLocation().getOrderDetailFileDir());
        for (String[] orderDetailData : Objects.requireNonNull(dataFile)) {
            OrderDetail details = new OrderDetail(orderDetailData[0], orderDetailData[1], orderDetailData[2], products.get(orderDetailData[3]), Integer.parseInt(orderDetailData[4]));
            orderDetails.add(details);
        }
    }

    private static void loadAllOrdersNoDetail() {
        ArrayList<String[]> dataFile = getDataFromFile(new FileLocation().getOrderFileDir());
        for (String[] orderData : Objects.requireNonNull(dataFile)) {
            Order order = new Order(orderData[0], orderData[1], LocalDate.parse(orderData[2], new DateMiddleware().dateParser()), Double.parseDouble(orderData[3]));
            orders.add(order);
        }
    }

    private static void loadAllCartsNoDetail() {
        ArrayList<String[]> dataFile = getDataFromFile(new FileLocation().getCartFileDir());
        for (String[] cartData : Objects.requireNonNull(dataFile)) {
            Cart cart = new Cart(cartData[0], cartData[1]);
            carts.add(cart);
        }
    }

    private static void loadAllOrders() {
        for (Order order : orders) {
            for (OrderDetail details : orderDetails) {
                if (order.getOrderId().equals(details.getOrderId())) {
                    order.addOrderDetailsToOrder(details);
                }
            }
            users.get(order.getUserId()).addOrderToList(order);
        }
    }

    private static void loadAllCarts() {
        for (Cart cart : carts) {
            for (OrderDetail details : orderDetails) {
                if (cart.getCartId().equals(details.getCartId())) {
                    cart.addItemToCart(details);
                }
            }
            users.get(cart.getUserId()).addCard(cart);
        }
    }

    private static void transferAllUsers() {
        try {
            FileWriter writer = new FileWriter(new FileLocation().getUserFileDir(), false);
            for (Map.Entry<String, User> user : users.entrySet()) {
                writer.write(user.getValue().getUserId() + ";"
                        + user.getValue().getUserName() + ";"
                        + user.getValue().getPassword() + ";"
                        + user.getValue().getFullName() + ";"
                        + user.getValue().getAddress() + ";"
                        + user.getValue().getPhoneNum() + ";"
                        + user.getValue().getBalance() + ";"
                        + user.getValue().getImageLocation() + "\n");
            }
            writer.close();
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    private static void transferAllAccounts() {
        try {
            FileWriter writer = new FileWriter(new FileLocation().getAccountFileDir(), false);
            for (Map.Entry<String, Account> account : accounts.entrySet()) {
                writer.write(account.getValue().getAccountId() + ";"
                        + account.getValue().getAccountType() + ";"
                        + account.getValue().getPoints() + ";"
                        + account.getValue().getNumReturnedItems() + ";"
                        + account.getValue().getIsAllowed2DaysItems() + ";"
                        + account.getValue().getRentalThreshold() + ";"
                        + account.getValue().getIsCurrentlyBorrowed() + ";"
                        + account.getValue().getOwner().getUserId() + "\n");
            }
            writer.close();
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    private static void transferAllProduct() {
        try {
            FileWriter writer = new FileWriter(new FileLocation().getProductFileDir(), false);
            for (Map.Entry<String, Product> product : products.entrySet()) {
                writer.write(product.getValue().getId() + ";"
                        + product.getValue().getTitle() + ";"
                        + product.getValue().getRentalType() + ";"
                        + product.getValue().getGenre() + ";"
                        + product.getValue().getPublishedYear() + ";"
                        + product.getValue().getNumOfCopies() + ";"
                        + product.getValue().getRentalFee() + ";"
                        + product.getValue().getLoanType() + ";"
                        + product.getValue().getStatus() + ";"
                        + product.getValue().getImageLocation() + "\n");
            }
            writer.close();
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    private static void transferAllOrderDetails() {
        try {
            FileWriter writer = new FileWriter(new FileLocation().getOrderDetailFileDir(), false);
            for (Map.Entry<String, User> user : users.entrySet()) {
                if(user.getValue().getRentalList() != null){
                    for (Order order : user.getValue().getRentalList()) {
                        for (OrderDetail detail : order.getOrders()) {
                            writer.write(detail.getOrderDetailId() + ";"
                                    + order.getOrderId() + ";"
                                    + "NaN" + ";"
                                    + detail.getBoughtItem().getId() + ";"
                                    + detail.getQuantity() + "\n");
                        }
                    }
                }
                if(user.getValue().getCart() != null){
                    for (OrderDetail detail : user.getValue().getCart().getShoppingItems()) {
                        writer.write(detail.getOrderDetailId() + ";"
                                + "NaN" + ";"
                                + user.getValue().getCart().getCartId() + ";"
                                + detail.getBoughtItem().getId() + ";"
                                + detail.getQuantity() + "\n");
                    }
                }
            }
            writer.close();
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    private static void transferAllCarts() {
        try {
            FileWriter writer = new FileWriter(new FileLocation().getCartFileDir(), false);
            for (Map.Entry<String, User> user : users.entrySet()) {
                if (user.getValue().getCart() == null) {
                    continue;
                }
                writer.write(user.getValue().getCart().getCartId() + ";"
                        + user.getValue().getUserId() + "\n");
            }
            writer.close();
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    private static void transferAllOrders() {
        try {
            FileWriter writer = new FileWriter(new FileLocation().getOrderFileDir(), false);
            for (Map.Entry<String, User> user : users.entrySet()) {
                for (Order order : user.getValue().getRentalList()) {
                    writer.write(order.getOrderId() + ";"
                            + user.getValue().getUserId() + ";"
                            + new DateMiddleware().dateAfterFormat(order.getOrderDate()) + ";"
                            + order.getTotalPrice() + "\n");
                }
            }
            writer.close();
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    public static void loadAllData() {
        loadAllCartsNoDetail();
        loadAllUsersNoAccounts();
        loadAllAccounts();
        loadAllProducts();
        loadAllOrderDetails();
        loadAllOrdersNoDetail();
        loadAllOrders();
        loadAllCarts();
    }

    public static void transferAllData() {
        transferAllUsers();
        transferAllAccounts();
        transferAllOrderDetails();
        transferAllCarts();
        transferAllOrders();
    }


    public static User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        DataAccess.currentUser = currentUser;
    }

    public void addAccountToList(Account account) {
        accounts.put(account.getAccountId(), account);
    }

    public static HashMap<String, User> getAllUsers() {
        return users;
    }

    public static HashMap<String, Account> getAllAccounts() {
        return accounts;
    }

    public static HashMap<String, Product> getAllProducts() {
        return products;
    }

    public static ArrayList<Cart> getAllCarts() {
        return carts;
    }

    public static ArrayList<Order> getAllOrders() {
        return orders;
    }

    public static void setChosenProduct(Product product) {
        DataAccess.chosenProduct = product;
    }

    public static Product getChosenProduct() {
        return chosenProduct;
    }

    public static Order getCurrentOrder() {
        return currentOrder;
    }

    public static void setCurrentOrder(Order currentOrder) {
        DataAccess.currentOrder = currentOrder;
    }
}
