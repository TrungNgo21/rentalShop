package Service;

import DataAccess.DataAccess;
import Model.Order.OrderDetail;
import java.util.HashMap;
import java.util.UUID;

public class OrderDetailCartService implements Services<OrderDetail>{
    private final DataAccess db = new DataAccess();

    @Override
    public String idCreation() {
        UUID uuid = UUID.randomUUID();
        return "OD00" + uuid;
    }

    @Override
    public void add(OrderDetail detail) {
        DataAccess.getCurrentUser().getCart().getShoppingItems().add(detail);
    }
    public void addToGlobal(OrderDetail detail){
        DataAccess.getOrderAdminDetails().add(detail);
    }

    @Override
    public void edit(OrderDetail detail) {
        for(OrderDetail orderDetail : DataAccess.getCurrentUser().getCart().getShoppingItems()){
            if(detail.getOrderDetailId().equals(orderDetail.getOrderDetailId())){
                delete(orderDetail);
            }
        }
        DataAccess.getCurrentUser().getCart().getShoppingItems().add(detail);
    }

    @Override
    public void delete(OrderDetail detail) {
        DataAccess.getCurrentUser().getCart().getShoppingItems().remove(detail);
    }

    @Override
    public OrderDetail getOne(String id) {
        for(OrderDetail detail : DataAccess.getCurrentUser().getCart().getShoppingItems()){
            if(id.equals(detail.getOrderDetailId())){
                return detail;
            }
        }
        return null;
    }

    @Override
    public HashMap<String, OrderDetail> getAll() {
        HashMap<String, OrderDetail> orderDetails = new HashMap<>();
        for(OrderDetail detail : DataAccess.getCurrentUser().getCart().getShoppingItems()){
                orderDetails.put(detail.getOrderDetailId(), detail);
        }
        return orderDetails;
    }
}
