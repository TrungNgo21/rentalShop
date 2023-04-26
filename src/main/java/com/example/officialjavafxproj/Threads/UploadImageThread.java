package com.example.officialjavafxproj.Threads;

import com.example.officialjavafxproj.Utils.FileController;

import java.io.File;

public class UploadImageThread implements Runnable {
    private final File targetFile;
    private final File uploadedFile;
    private final int finalWidth;
    private final int finalHeight;


    public UploadImageThread(File targetFile, File uploadedFile, int finalWidth, int finalHeight) {
        this.targetFile = targetFile;
        this.uploadedFile = uploadedFile;
        this.finalWidth = finalWidth;
        this.finalHeight = finalHeight;
    }

    @Override
    public void run() {
        FileController.uploadFile(targetFile, uploadedFile, finalWidth, finalHeight);
    }
}
