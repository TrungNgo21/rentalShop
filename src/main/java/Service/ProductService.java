package Service;

import DataAccess.DataAccess;
import Model.Product.Product;

import java.util.*;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;



public class ProductService implements Services<Product>{


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
                        System.out.println(option);
                        if(sysProduct.getValue().getRentalType().equals(option)){
                            DataAccess.addToSortedProducts(sysProduct.getValue());
                        }
                    }
                }
                if(noneCounter == sortedOptions.get(i).length){
                    getSortedProducts().clear();
                    for(Map.Entry<String, Product> sysProduct : getAll().entrySet()){
                        DataAccess.addToSortedProducts(sysProduct.getValue());
                    }
                }
            }else if(i == 1){
                boolean isExisted = false;
                int noneCounter = 0;
                deletedProductId.clear();
                System.out.println(getSortedProducts());
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
                    }
                    noneCounter = 0;

                }

                for(String deletedId : deletedProductId){
                    getSortedProducts().remove(deletedId);
                }


            }else if(i == 2){
                boolean isExisted = false;
                int noneCounter = 0;

                deletedProductId.clear();
                System.out.println(getSortedProducts());
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
                    }
                    noneCounter = 0;
                }
                for(String deletedId : deletedProductId){
                    getSortedProducts().remove(deletedId);
                }
            }else{
                boolean isExisted = false;
                int noneCounter = 0;

                deletedProductId.clear();
                System.out.println(getSortedProducts());
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
                    }
                    noneCounter = 0;

                }

                for(String deletedId : deletedProductId){
                    getSortedProducts().remove(deletedId);
                }

            }
        }
    }

}
