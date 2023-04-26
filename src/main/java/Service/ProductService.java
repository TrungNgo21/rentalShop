package Service;

import DataAccess.DataAccess;
import Model.Product.Product;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductService implements Services<Product>{
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
        return null;
    }

    @Override
    public HashMap<String,Product> getAll() {
        return null;
    }
}
