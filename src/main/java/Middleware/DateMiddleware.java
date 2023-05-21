package Middleware;

import com.example.officialjavafxproj.Utils.LineChartBuilder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Date;

public class DateMiddleware {
    public DateTimeFormatter dateParser(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return formatter;
    }

    public String dateAfterFormat(LocalDate localDate){
        DateTimeFormatter parser = dateParser();
        return parser.format(localDate);
    }

}
