package app.core;

import app.utils.ImageUtils;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;

import java.awt.image.BufferedImage;

public class MorphologicalOperations {
    BufferedImage image;
    public MorphologicalOperations (BufferedImage image) {
        this.image = image;
    }

    public BufferedImage erode() {
        Mat source = ImageUtils.convertBufferedImageToMat(image);
        Mat destination = new Mat(source.rows(), source.cols(), source.type());
        Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3, 3));

        Imgproc.erode(source, destination, kernel);
        return (BufferedImage) HighGui.toBufferedImage(destination);
    }

    public BufferedImage dilation() {
        Mat source = ImageUtils.convertBufferedImageToMat(image);
        Mat destination = new Mat(source.rows(), source.cols(), source.type());
        Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3, 3));

        Imgproc.dilate(source, destination, kernel);
        return (BufferedImage) HighGui.toBufferedImage(destination);
    }

    public BufferedImage open() {
        Mat source = ImageUtils.convertBufferedImageToMat(image);
        Mat destination = new Mat(source.rows(), source.cols(), source.type());
        Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3, 3));

        Imgproc.morphologyEx(source, destination, Imgproc.MORPH_OPEN, kernel);
        return (BufferedImage) HighGui.toBufferedImage(destination);
    }

    public BufferedImage close() {
        Mat source = ImageUtils.convertBufferedImageToMat(image);
        Mat destination = new Mat(source.rows(), source.cols(), source.type());
        Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3, 3));

        Imgproc.morphologyEx(source, destination, Imgproc.MORPH_CLOSE, kernel);
        return (BufferedImage) HighGui.toBufferedImage(destination);
    }
}
