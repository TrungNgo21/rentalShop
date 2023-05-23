package com.example.officialjavafxproj.Utils;

import Model.Product.Product;
import Service.FeedbackService;

import java.util.Comparator;

public class TopProductComparator implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        if(new FeedbackService().getAverageRatings(o1.getId()) >= new FeedbackService().getAverageRatings(o2.getId())){
            return -1;
        }else{
            return 1;
        }
//        return (int) (new FeedbackService().getAverageRatings(o2.getId()) - new FeedbackService().getAverageRatings(o1.getId()));
    }
}
