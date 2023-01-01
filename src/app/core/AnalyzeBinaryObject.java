package app.core;

import app.utils.ImageUtils;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AnalyzeBinaryObject {
    BufferedImage image;
    private Random rng = new Random(12345);

    public AnalyzeBinaryObject (BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getMoments() {
        Mat source = ImageUtils.convertBufferedImageToMat(image);

        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();


        Moments moments = Imgproc.moments(source);

        Imgproc.findContours(source, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
        List<Moments> mu = new ArrayList<>(contours.size());
        for (int i = 0; i < contours.size(); i++) {
            mu.add(Imgproc.moments(contours.get(i)));
        }

        List<Point> mc = new ArrayList<>(contours.size());
        for (int i = 0; i < contours.size(); i++) {
            //add 1e-5 to avoid division by zero
            mc.add(new Point(mu.get(i).m10 / (mu.get(i).m00 + 1e-5), mu.get(i).m01 / (mu.get(i).m00 + 1e-5)));
        }

        Mat drawing = Mat.zeros(source.size(), CvType.CV_8UC3);
        for (int i = 0; i < contours.size(); i++) {
            Scalar color = new Scalar(rng.nextInt(256), rng.nextInt(256), rng.nextInt(256));
            Imgproc.drawContours(drawing, contours, i, color, 2);
            Imgproc.circle(drawing, mc.get(i), 4, color, -1);
            double area = Imgproc.contourArea(contours.get(i));
            System.out.println("rozmiar " + i + " = " + area);
            MatOfPoint2f c2f = new MatOfPoint2f(contours.get(i).toArray());
            System.out.println("obwod " + i + " = " + Imgproc.arcLength(c2f, true));
            Rect rect = Imgproc.boundingRect(contours.get(i));

            MatOfInt matOfInt = new MatOfInt();
            Imgproc.convexHull(contours.get(i), matOfInt);

            double hullArea = 1.0;
            try {
                hullArea = Imgproc.contourArea(matOfInt);
            } catch(Exception e) {
                System.out.println("error hull");
            }

            System.out.println("aspect ratio " + i + " = " + (float)(rect.width / rect.height));
            System.out.println("extent " + i + " = " + (float)(area / (rect.width * rect.height)));
            System.out.println("solidity " + i + " = " + (float)area / hullArea);
            System.out.println("Equivalent Diameter " + i + " = " + Math.sqrt(4*area/Math.PI));
        }

        return ImageUtils.convertMatToBufferedImage(drawing);
    }
}
