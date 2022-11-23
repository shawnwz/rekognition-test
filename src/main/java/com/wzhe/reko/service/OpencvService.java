package com.wzhe.reko.service;


import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static org.opencv.imgcodecs.Imgcodecs.imread;

@Service
public class OpencvService {

    @Value("${opencv.model-path}")
    private static String modelPath;

    public int checkFaceNo(String path)
    {
        //String path = System.getProperty("user.dir").concat("/haarcascades/haarcascade_frontalface_alt.xml");
//        CascadeClassifier faceDetector = new CascadeClassifier(path);
//        MatOfRect faceDetections = new MatOfRect();
//        faceDetector.detectMultiScale(StreamUtils.base642Mat(base64), faceDetections);
//        return faceDetections.toArray().length;

        //https://github.com/zq2599/blog_demos/blob/master/javacv-tutorials/face-detect-demo/src/main/java/com/bolingcavalry/facedetect/controller/UploadController.java

        Mat srcImg = Imgcodecs.imread("/Users/zhe/Data/Green Screen/PAFL_20200913T165626000-0400_D9ZdQ-RuROWcYwbDNnrVug.jpg");
        //Mat srcImg = imread("/Users/zhe/Data/Green Screen/PAFL_20200913T165626000-0400_D9ZdQ-RuROWcYwbDNnrVug.jpg");
        Mat dstGrayImg = new Mat();
        Imgproc.cvtColor(srcImg, dstGrayImg, Imgproc.COLOR_BGR2GRAY);
        CascadeClassifier classifier = new CascadeClassifier(modelPath);

        MatOfRect faceRect = new MatOfRect();
        Size minSize = new Size(32, 32);
        double scaleFactor = 1.2;
        int minneighbors = 2;   //how many neighbors should each candidate rectangle have to retain it
        classifier.detectMultiScale(dstGrayImg, faceRect, scaleFactor, minneighbors, 0, minSize);

        Rect[] rects = faceRect.toArray();

        if (null==rects || rects.length<1) {
            // no face
            return 0;
        } else {
            return rects.length;
        }

    }

}
