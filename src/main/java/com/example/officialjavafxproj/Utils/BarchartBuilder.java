package com.example.officialjavafxproj.Utils;

import Model.Product.Product;
import Service.ProductService;
import com.example.officialjavafxproj.Controller.Component.BarChartControllers;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;

public class BarchartBuilder {
    private BarChart<String, Number> barChart;
    private CategoryAxis xAxis = new CategoryAxis();
    private NumberAxis yAxis = new NumberAxis();

    private BarchartBuilder(){
        barChart = new BarChart<String, Number>(xAxis, yAxis);
    }

    public static BarchartBuilder builder(){
        return new BarchartBuilder();
    }

    public BarchartBuilder withXCategories(ObservableList<String> categories){
        xAxis.setCategories(categories);
        return this;
    }

    public BarchartBuilder withXAxis(String xLabel){
        barChart.getXAxis().setLabel(xLabel);
        return this;
    }

    public BarchartBuilder withYAxis(String yLabel){
        barChart.getYAxis().setLabel(yLabel);
        return this;
    }

    public BarchartBuilder withProductData(ArrayList<String[]> data, String[] groups){
        for(String group : groups){
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            for(String[] dataElement : data){
                if(dataElement[2].equals(group)){
                    series.setName(dataElement[2]);
                    series.getData().add(new XYChart.Data<>(dataElement[0], Double.parseDouble(dataElement[1]), dataElement[2]));
                }
            }
            System.out.println(series.getData());
            barChart.getData().add(series);
        }
        return this;
    }


    public BarChart<String, Number> build(){
        return barChart;
    }

}
