import DataAccess.DataAccess;
import Service.ProductService;
import com.example.officialjavafxproj.Utils.FileController;

import java.io.File;

public class trung {
    public static void main(String[] args) {
        DataAccess.loadAllData();
        ProductService productService = new ProductService();
        System.out.println(productService.sortByTitle("GAME"));
    }
}
