package Service;

import DataAccess.DataAccess;
import Model.Form.Feedback;

import java.util.ArrayList;
import java.util.Map;

import static java.lang.Math.round;

public class FeedbackService {


    public void addFeedbackToDb(Feedback feedback){
        DataAccess.addFeedback(feedback);
    }

    public ArrayList<Feedback> getAllReviews(){
//        for(Feedback feedback : DataAccess.getFeedbacks()){
//            System.out.println(feedback.getRating());
//        }
        return DataAccess.getFeedbacks();
    }

    public double getAverageRatings(String itemId){
        double total = 0;
        double totalFeedbacks = 0;
        for(Feedback feedback : getAllReviews()){
            if(feedback.getProductId().equals(itemId)){
                total += feedback.getRating();
                totalFeedbacks++;
            }
        }
        return total/totalFeedbacks;
    }
}
