package Model.User;

import DataAccess.DataAccess;
import Model.Account.Account;
import Model.Order.Cart;
import Model.Order.Order;

import java.util.ArrayList;

public abstract class User {
    private String userId;
    private String userName;
    private String password;
    private String fullName;
    private String address;
    private String phoneNum;

    private double balance;
    private Account account;

    private ArrayList<Order> rentalList;

    private Cart cart;

    private String imageLocation;

    public User(String userId, String userName, String password, String fullName, String address, String phoneNum, double balance, Account account, String imageLocation) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.address = address;
        this.phoneNum = phoneNum;
        this.balance = balance;
        this.account = account;
        this.imageLocation = imageLocation;
    }

    public User(String userId, String userName, String password, String fullName, String address, String phoneNum, String imageLocation) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.address = address;
        this.phoneNum = phoneNum;
        this.imageLocation = imageLocation;
    }

    public User(){}

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public Cart getCart() {
        return cart;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public double getBalance() {
        return balance;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public void addOrderToList(Order order){
        this.rentalList.add(order);
    }

    public ArrayList<Order> getRentalList() {
        return rentalList;
    }

    public void addCard(Cart cart){
        this.cart = cart;
    }
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", balance=" + balance +
                ", account=" + account +
                ", rentalList=" + rentalList +
                ", cart=" + cart +
                ", imageLocation='" + imageLocation + '\'' +
                '}';
    }
}
