package app.utils;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageUtils {
    public static BufferedImage convertMatToBufferedImage(Mat mat) {
        MatOfByte matOfByte = new MatOfByte();
        Imgcodecs.imencode(".jpg", mat, matOfByte);
        byte[] byteArray = matOfByte.toArray();
        InputStream in = new ByteArrayInputStream(byteArray);
        BufferedImage bufImage = null;
        try {
            bufImage = ImageIO.read(in);
        } catch (IOException ex) {
            System.out.println("Błąd tworzenia Mat");
        }

        return bufImage;
    }

    public static Mat convertBufferedImageToMat(BufferedImage image) {
        Mat imageMat = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC1);
        byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        imageMat.put(0, 0, data);

        return imageMat;
    }
}
