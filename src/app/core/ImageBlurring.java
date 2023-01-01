package app.core;

import app.utils.ImageUtils;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageBlurring {
    BufferedImage image;
    public ImageBlurring (BufferedImage image) {
        this.image = image;
    }

    public BufferedImage blur() {
        Mat source = ImageUtils.convertBufferedImageToMat(image);

        Mat destination = new Mat(source.rows(), source.cols(), source.type());

        Size size = new Size(45, 45);
        Point point = new Point(20, 30);

        Imgproc.blur(source, destination, size, point, Core.BORDER_DEFAULT);
        Image img = HighGui.toBufferedImage(destination);

        return (BufferedImage) img;
    }

    public BufferedImage blurWithWeight() {
        Mat source = ImageUtils.convertBufferedImageToMat(image);

        Mat destination = new Mat(source.rows(), source.cols(), source.type());

        Imgproc.bilateralFilter(source, destination, 9, 75, 75);
        Image img = HighGui.toBufferedImage(destination);

        return (BufferedImage) img;
    }

    public BufferedImage gaussianBlur() {
        Mat source = ImageUtils.convertBufferedImageToMat(image);

        Mat destination = new Mat(source.rows(), source.cols(), source.type());

        Imgproc.GaussianBlur(source, destination, new Size(15, 15), 0);
        Image img = HighGui.toBufferedImage(destination);

        return (BufferedImage) img;
    }

    public BufferedImage testSource() {
        Mat source = ImageUtils.convertBufferedImageToMat(image);
        return (BufferedImage) HighGui.toBufferedImage(source);
    }
}
