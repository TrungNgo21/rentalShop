package com.example.officialjavafxproj.Utils;

import DataAccess.DataAccess;
import Model.Product.Product;

import java.util.HashMap;
import java.util.Map;

public class SearchController {
    private static HashMap<String,Product> tempContainer = new HashMap<String,Product>();

    private static void addProduct(Product product){tempContainer.put(product.getId(),product);}

    public static HashMap<String, Product> getTempContainer() {return tempContainer;}

    public static boolean searchByString(String input, String searchField){
        if(input.equalsIgnoreCase(searchField) || searchField.toLowerCase().contains(input.toLowerCase())){
            return true;
        }
        else {
            return false;
        }
    }
    public static void searchByIdentify(String productIdentifier, HashMap<String,Product> productHashMap) {
        tempContainer.clear();
        for(Map.Entry<String,Product> product : productHashMap.entrySet()){
            if(SearchController.searchByString(productIdentifier,product.getValue().getTitle())){
                SearchController.addProduct(product.getValue());
            }
        }
    }
}
