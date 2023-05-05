import com.example.officialjavafxproj.Utils.FileController;

import java.io.File;

public class trung {
    public static void main(String[] args) {
        int width = 400;
        int height = 400;
        String path = "/Users/trungngo/Desktop/OfficialJavaFXProj/src/main/resources/com/example/officialjavafxproj/Image/Public/default.png";
        String targetPath = "/Users/trungngo/Desktop/OfficialJavaFXProj/src/main/resources/com/example/officialjavafxproj/Image/Users/C007.png";
        FileController.uploadFile(new File(targetPath), new File(path), width, height);
    }
}
