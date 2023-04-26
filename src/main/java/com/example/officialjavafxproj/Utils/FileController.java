package com.example.officialjavafxproj.Utils;

import Middleware.ImageMiddleware;
import com.example.officialjavafxproj.Controller.RegisterControllers;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

public class FileController{

    public static void uploadFile(File targetFile, File uploadedFile, int width, int height){
        ImageMiddleware imageMiddleware = new ImageMiddleware();
        try {
            BufferedImage bufferedImage = ImageIO.read(uploadedFile);
            BufferedImage rescaleImage = imageMiddleware.rescaleImage(width, height, bufferedImage);
            String extension = getFileExtension(uploadedFile);
            ImageIO.write(rescaleImage, extension, targetFile);
                System.out.println("File uploaded and saved successfully.");
        }catch (IOException err){
            err.printStackTrace();
        }
    }

    public static String getFileExtension(File inputFile){
        String fileName = inputFile.toString();
        String extension = "";
        int index = fileName.lastIndexOf('.');
        if(index > 0) {
            extension = fileName.substring(index + 1);
        }
        return extension;
    }

    public static void deleteFile(File deleteFile){
        deleteFile.delete();
    }
}
