package Service;

import DataAccess.DataAccess;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.TreeMap;

public class RevenueService {
    public void addToRevenue(LocalDate date, double income){
        DataAccess.addRevenue(date, income);
    }

    public double getRevenue(){
        return DataAccess.getTotalRevenue();
    }

    public TreeMap<LocalDate, Double> getAllRevenue(){
       return DataAccess.getRevenueDaily();
    }
}
