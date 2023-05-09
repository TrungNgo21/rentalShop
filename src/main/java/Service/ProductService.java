package Service;

import DataAccess.DataAccess;
import Model.Product.Product;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ProductService implements Services<Product> {

    private final DataAccess db = new DataAccess();

    @Override
    public String idCreation() {
        int numOfProduct = DataAccess.getAllProducts().size();
        if (numOfProduct < 10) {
            return "C00" + numOfProduct + " - ";
        } else if (numOfProduct <= 99) {
            return "C0" + numOfProduct + " - ";
        } else {
            return "C" + numOfProduct + " - ";
        }
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
    public HashMap<String, Product> getAll() {
        return DataAccess.getAllProducts();
    }

    public void setTargetProduct(Product currentProduct) {
        DataAccess.setChosenProduct(currentProduct);
    }

    public Product getTargetProduct() {
        return DataAccess.getChosenProduct();
    }

    public void addSortedOptions(String[] options){
        DataAccess.addSortedOptions(options);
    }

    public ArrayList<String[]> getSortedOptions(){
        return DataAccess.getSortedOptions();
    }

    public HashMap<String, Product> getSortedProducts(){
        return DataAccess.getSortedProducts();
    }

    public void addToSortedProducts(ArrayList<String[]> sortedOptions){
        DataAccess.getSortedProducts().clear();
        System.out.println("sort options: " + sortedOptions.size() );
        ArrayList<String> deletedProductId = new ArrayList<>();
        for (int i = 0; i < sortedOptions.size(); i++) {

            if(i == 0){
                int noneCounter = 0;
                for(String option : sortedOptions.get(i)){
                    if(option.equals("NONE")){
                        noneCounter++;
                        continue;
                    }

                    for(Map.Entry<String, Product> sysProduct : getAll().entrySet()){
                        if(sysProduct.getValue().getRentalType().equals(option)){
                            DataAccess.addToSortedProducts(sysProduct.getValue());
                        }
                    }
                }
                if(noneCounter == sortedOptions.get(i).length){
                    DataAccess.setSortedProducts(DataAccess.getAllProducts());
                }
            }else if(i == 1){
                boolean isExisted = false;
                int noneCounter = 0;
                deletedProductId.clear();
                for(Map.Entry<String, Product> sysProduct : getSortedProducts().entrySet()){
                    for(String option : sortedOptions.get(i)){
                        if(option.equals("NONE")){
                            noneCounter++;
                            continue;
                        }
                        if(sysProduct.getValue().getGenre().equals(option)){
                            isExisted = true;
                            break;
                        }
                    }
                    if(!isExisted){
                        deletedProductId.add(sysProduct.getKey());
                    }
                    isExisted = false;
                    if(noneCounter == sortedOptions.get(i).length){
                        deletedProductId.clear();
                        noneCounter = 0;
                    }
                }

                for(String deletedId : deletedProductId){
                    getSortedProducts().remove(deletedId);
                }


            }else if(i == 2){
                boolean isExisted = false;
                int noneCounter = 0;

                deletedProductId.clear();
                for(Map.Entry<String, Product> sysProduct : getSortedProducts().entrySet()){
                    for(String option : sortedOptions.get(i)){
                        if(option.equals("NONE")){
                            noneCounter++;
                            continue;
                        }
                        if(sysProduct.getValue().getLoanType().equals(option)){
                            isExisted = true;
                            break;
                        }
                    }
                    if(!isExisted){
                        deletedProductId.add(sysProduct.getKey());
                    }
                    isExisted = false;
                    if(noneCounter == sortedOptions.get(i).length){
                        deletedProductId.clear();
                        noneCounter = 0;

                    }
                }
                for(String deletedId : deletedProductId){
                    getSortedProducts().remove(deletedId);
                }
            }else{
                boolean isExisted = false;
                int noneCounter = 0;

                deletedProductId.clear();
                for(Map.Entry<String, Product> sysProduct : getSortedProducts().entrySet()){
                    for(String option : sortedOptions.get(i)){
                        if(option.equals("NONE")){
                            noneCounter++;
                            continue;
                        }
                        if(sysProduct.getValue().getStatus().equals(option)){
                            isExisted = true;
                            break;
                        }
                    }
                    if(!isExisted){
                        deletedProductId.add(sysProduct.getKey());
                    }
                    isExisted = false;
                    if(noneCounter == sortedOptions.get(i).length){
                        deletedProductId.clear();
                        noneCounter = 0;
                    }
                }

                for(String deletedId : deletedProductId){
                    getSortedProducts().remove(deletedId);
                }

            }
        }
    }


}
