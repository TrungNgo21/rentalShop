package Service;

import DataAccess.DataAccess;
import Model.Product.Product;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProductService implements Services<Product>{

    private final DataAccess db = new DataAccess();
    @Override
    public String idCreation() {
        int numOfProduct = DataAccess.getAllProducts().size();
        return "I00" + numOfProduct + " - ";
    }

    @Override
    public void add(Product product) {
        DataAccess.getAllProducts().put(idCreation() + product.getPublishedYear(), product);
    }

    @Override
    public void edit(Product product) {
        DataAccess.getAllProducts().put(product.getId(), product);

    }

    @Override
    public void delete(Product template) {

    }

    @Override
    public Product getOne(String productId) {
        return DataAccess.getAllProducts().get(productId);
    }

    @Override
    public HashMap<String,Product> getAll() {
        return DataAccess.getAllProducts();
    }

    public void setTargetProduct(Product currentProduct){
        DataAccess.setChosenProduct(currentProduct);
    }

    public Product getTargetProduct(){
        return DataAccess.getChosenProduct();
    }

}
