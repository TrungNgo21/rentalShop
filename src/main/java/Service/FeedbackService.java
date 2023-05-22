package Service;

import DataAccess.DataAccess;
import Model.Form.Feedback;

import java.util.ArrayList;

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
}
