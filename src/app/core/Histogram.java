package app.core;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class Histogram {
    public int[] mono;
    public int[][] rgb;

    public Histogram(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        int channels = image.getColorModel().getNumComponents();

        rgb = new int[3][width * height];
        mono = new int[width * height];

        final byte[] a = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();

        if (channels == 3) {
            for (int i = 0; i < width * height * 3; i += 3) {
                rgb[0][i] = a[i + 2] & 0xFF;
                rgb[1][i] = a[i + 1] & 0xFF;
                rgb[2][i] = a[i] & 0xFF;
            }
            for (int i = 0; i < width * height; ++i) mono[i] = rgb[0][i] + rgb[1][i] + rgb[2][i];
        } else {
            for (int i = 0; i < width * height; i++) {
                mono[i] = a[i] & 0xFF;
                rgb[0][i] = a[i] & 0xFF;
                rgb[1][i] = a[i] & 0xFF;
                rgb[2][i] = a[i] & 0xFF;
            }
        }

    }
}
