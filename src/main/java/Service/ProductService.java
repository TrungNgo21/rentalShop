package Service;

import DataAccess.DataAccess;
import Model.Product.Product;

import java.util.*;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.HashMap;


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

    public Product getOne(String productIdentifier) {
        for(Map.Entry<String,Product> product : DataAccess.getAllProducts().entrySet()){
            if(productIdentifier.equals(product.getValue().getId()) || productIdentifier.equals(product.getValue().getTitle())){
                return product.getValue();
            }
        }
        return null;

    }
    public Product getStock(){
        for(Map.Entry<String,Product> product : DataAccess.getAllProducts().entrySet()){
            if(product.getValue().getNumOfCopies() == 0){
                return product.getValue();
            }
        }
        return null;
    }
    public HashMap<String, Product> sortById(String type){
        DataAccess.getSortedProducts().clear();
        HashMap<String,Product> sortedByType = getProductByType(type);
        List<Map.Entry<String,Product> > list = new LinkedList<Map.Entry<String,Product> >(sortedByType.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Product>>() {
            @Override
            public int compare(Map.Entry<String, Product> o1, Map.Entry<String, Product> o2) {
                return (o1.getValue().getId().compareTo(o2.getValue().getId()));
            }
        });
        HashMap<String,Product> temp = new LinkedHashMap<String,Product>();
        for (Map.Entry<String,Product> product : list){
            temp.put(product.getKey(),product.getValue());
        }
        return temp;
    }
    public HashMap<String,Product> sortByTitle(String type){
//        //clear
        DataAccess.getSortedProducts().clear();
        HashMap<String,Product> sortedByType = getProductByType(type);
        List<Map.Entry<String,Product> > list = new LinkedList<Map.Entry<String,Product> >(sortedByType.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Product>>() {
            @Override
            public int compare(Map.Entry<String, Product> o1, Map.Entry<String, Product> o2) {
                return (o1.getValue().getTitle().compareTo(o2.getValue().getTitle()));
            }
        });
        HashMap<String,Product> temp = new LinkedHashMap<String,Product>();
        for (Map.Entry<String,Product> product : list){
            temp.put(product.getKey(),product.getValue());
        }
        return temp;
    }
    public HashMap<String,Product> getProductByType(String rentalType){
        DataAccess.getSortedProducts().clear();
        for(Map.Entry<String,Product> product : DataAccess.getAllProducts().entrySet()){
            if(product.getValue().getRentalType().equals(rentalType)){
                DataAccess.getSortedProducts().put(product.getKey(),product.getValue());
            }
        }

        return DataAccess.getSortedProducts();
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
