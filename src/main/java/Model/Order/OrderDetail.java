package Model.Order;

import Model.Product.Product;
;

public class OrderDetail {
    private String orderId;

    private String cartId;
    private Product boughtItem;
    private int quantity;

    public OrderDetail(String orderId, String cartId, Product boughtItem, int quantity) {
        this.orderId = orderId;
        this.cartId = cartId;
        this.boughtItem = boughtItem;
        this.quantity = quantity;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCartId() {
        return cartId;
    }

    public Product getBoughtItem() {
        return boughtItem;
    }

    public int getQuantity() {
        return quantity;
    }
}
