package app.core;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.Arrays;

public class BinaryOperations {
    private BufferedImage image1;
    private BufferedImage image2;
    private int channels;

    private byte[] image1AsBytes;
    private byte[] image2AsBytes;

    private int image1Width;
    private int image2Width;

    private static final double rPerc = 0.2;
    private static final double gPerc = 0.6;
    private static final double bPerc = 0.2;

    public BinaryOperations(BufferedImage img1) {
        channels = img1.getColorModel().getNumComponents();
        if (channels > 1) img1 = rgbToGrayscale(img1);
        channels = 1;

        this.image1 = img1;

        image1AsBytes = ((DataBufferByte) image1.getRaster().getDataBuffer()).getData();

        image1Width = image1.getWidth();
    }

    public BinaryOperations(BufferedImage img1, BufferedImage img2) {
        channels = img1.getColorModel().getNumComponents();
        if (channels > 1) img1 = rgbToGrayscale(img1);
        channels = img2.getColorModel().getNumComponents();
        if (channels > 1) img2 = rgbToGrayscale(img2);
        channels = 1;

        this.image1 = img1;
        this.image2 = img2;

        image1AsBytes = ((DataBufferByte) image1.getRaster().getDataBuffer()).getData();
        image2AsBytes = ((DataBufferByte) image2.getRaster().getDataBuffer()).getData();

        image1Width = image1.getWidth();
        image2Width = image2.getWidth();
    }

    public static BufferedImage rgbToGrayscale(BufferedImage bufferedImage) {
        int channels = bufferedImage.getColorModel().getNumComponents();
        if (channels == 1) return bufferedImage;

        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        BufferedImage grayscaleImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

        final byte[] a = ((DataBufferByte) bufferedImage.getRaster().getDataBuffer()).getData();
        final byte[] gray = ((DataBufferByte) grayscaleImage.getRaster().getDataBuffer()).getData();
        for (int p = 0; p < width * height * channels; p += channels) {
            double r = a[p + 2] & 0xFF;
            double g = a[p + 1] & 0xFF;
            double b = a[p] & 0xFF;
            gray[p / channels] = (byte) Math.round((r * rPerc) + (g * gPerc) + (b * bPerc));

        }
        return grayscaleImage;
    }

    public BufferedImage addImages(boolean czyWysycenie) {
        if (this.image2 != null) {
            int width = Math.max(image1.getWidth(), image2.getWidth());
            int height = Math.max(image1.getHeight(), image2.getHeight());
            BufferedImage resultImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
            byte[] resultImageBytes = ((DataBufferByte) resultImage.getRaster().getDataBuffer()).getData();
            int[] tempImageBytes = new int[resultImageBytes.length];
            Arrays.fill(tempImageBytes, 255);
            int i2;
            for (int i = 0; i < image1AsBytes.length; ++i) {
                i2 = i1ToI2(i, image1Width, width);

                int wartosc = image1AsBytes[i] & 0xFF;
                if (czyWysycenie) {
                    wartosc = wartosc / 2;
                }
                tempImageBytes[i2] = wartosc;
            }

            for (int i = 0; i < image2AsBytes.length; ++i) {
                i2 = i1ToI2(i, image2Width, width);
                System.out.println("image2 " + image2AsBytes[i] + " " + (image2AsBytes[i] & 0xFF));
                int wartosc = image2AsBytes[i] & 0xFF;
                if (czyWysycenie) {
                    wartosc = wartosc / 2;
                }

                if (tempImageBytes[i2] + wartosc > 255) {
                    tempImageBytes[i2] = 255;
                } else {
                    tempImageBytes[i2] += wartosc;
                }
            }

            for (int i = 0; i < resultImageBytes.length; ++i) {
                resultImageBytes[i] = (byte) (Math.abs(tempImageBytes[i]));
            }

            return resultImage;
        }
        return null;
    }

    public BufferedImage numberOperation(String operation, int number, boolean czyWysycenie) {
        int width = image1.getWidth();
        int height = image1.getHeight();

        BufferedImage resultImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        byte[] resultImageBytes = ((DataBufferByte) resultImage.getRaster().getDataBuffer()).getData();
        int[] tempImageBytes = new int[resultImageBytes.length];
        Arrays.fill(tempImageBytes, 255);
        int i2;
        for (int i = 0; i < image1AsBytes.length; ++i) {
            i2 = i1ToI2(i, image1Width, width);

            int wartosc = image1AsBytes[i] & 0xFF;
            if (czyWysycenie) {
                wartosc = wartosc / 2;
            }
            tempImageBytes[i2] = wartosc;
        }

        switch (operation) {
            case "add": {
                for (int i = 0; i < image1AsBytes.length; ++i) {
                    i2 = i1ToI2(i, image1Width, width);
                    int wartosc = number;
                    if (czyWysycenie) {
                        wartosc = wartosc / 2;
                    }

                    if (tempImageBytes[i2] + wartosc > 255) {
                        tempImageBytes[i2] = 255;
                    } else {
                        tempImageBytes[i2] += wartosc;
                    }
                }
                break;
            }
            case "multiply": {
                for (int i = 0; i < image1AsBytes.length; ++i) {
                    i2 = i1ToI2(i, image1Width, width);

                    if (tempImageBytes[i2] * number > 255) {
                        tempImageBytes[i2] = 255;
                    } else {
                        tempImageBytes[i2] *= number;
                    }
                }
                break;
            }
            case "divide": {
                for (int i = 0; i < image1AsBytes.length; ++i) {
                    i2 = i1ToI2(i, image1Width, width);

                    if (tempImageBytes[i2] / number > 255) {
                        tempImageBytes[i2] = 255;
                    } else {
                        tempImageBytes[i2] /= number;
                    }
                }
                break;
            }
        }

        for (int i = 0; i < resultImageBytes.length; ++i) {
            resultImageBytes[i] = (byte) (Math.abs(tempImageBytes[i]));
        }

        return resultImage;
    }

    public BufferedImage logicalOperations(String operation) {
        int width = Math.max(image1.getWidth(), image2.getWidth());
        int height = Math.max(image1.getHeight(), image2.getHeight());
        BufferedImage resultImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        byte[] resultImageBytes = ((DataBufferByte) resultImage.getRaster().getDataBuffer()).getData();
        int[] tempImageBytes = new int[resultImageBytes.length];
        Arrays.fill(tempImageBytes, 255);
        int i2;

        for (int i = 0; i < image1AsBytes.length; ++i) {
            i2 = i1ToI2(i, image1Width, width);

            int wartosc = image1AsBytes[i] & 0xFF;
            tempImageBytes[i2] = wartosc;
        }

        switch (operation) {
            case "and": {
                for (int i = 0; i < image2AsBytes.length; ++i) {
                    i2 = i1ToI2(i, image2Width, width);
                    tempImageBytes[i2] &= image2AsBytes[i];
                }
            }
            case "or": {
                for (int i = 0; i < image2AsBytes.length; ++i) {
                    i2 = i1ToI2(i, image2Width, width);
                    tempImageBytes[i2] |= image2AsBytes[i];
                }
            }
            case "xor": {
                for (int i = 0; i < image2AsBytes.length; ++i) {
                    i2 = i1ToI2(i, image2Width, width);
                    tempImageBytes[i2] ^= image2AsBytes[i];
                }
            }
        }

        for (int i = 0; i < resultImageBytes.length; ++i) {
            resultImageBytes[i] = (byte) (Math.abs(tempImageBytes[i]));
        }

        return resultImage;
    }

    public BufferedImage not() {
        int width = image1.getWidth();
        int height = image1.getHeight();
        BufferedImage resultImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        byte[] resultImageBytes = ((DataBufferByte) resultImage.getRaster().getDataBuffer()).getData();
        int[] tempImageBytes = new int[resultImageBytes.length];
        Arrays.fill(tempImageBytes, 255);
        int i2;

        for (int i = 0; i < image1AsBytes.length; ++i) {
            i2 = i1ToI2(i, image1Width, width);

            int wartosc = image1AsBytes[i] & 0xFF;
            tempImageBytes[i2] = 255 - wartosc;
        }

        for (int i = 0; i < resultImageBytes.length; ++i) {
            resultImageBytes[i] = (byte) (Math.abs(tempImageBytes[i]));
        }

        return resultImage;
    }

    public BufferedImage binary(int number) {
        int width = image1.getWidth();
        int height = image1.getHeight();
        BufferedImage resultImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        byte[] resultImageBytes = ((DataBufferByte) resultImage.getRaster().getDataBuffer()).getData();
        int[] tempImageBytes = new int[resultImageBytes.length];
        Arrays.fill(tempImageBytes, 255);
        int i2;

        for (int i = 0; i < image1AsBytes.length; ++i) {
            i2 = i1ToI2(i, image1Width, width);

            int wartosc = image1AsBytes[i] & 0xFF;
            if(wartosc <= number) {
                tempImageBytes[i2] = 0;
            } else {
                tempImageBytes[i2] = 255;
            }
        }

        for (int i = 0; i < resultImageBytes.length; ++i) {
            resultImageBytes[i] = (byte) (Math.abs(tempImageBytes[i]));
        }

        return resultImage;
    }


    /**
     * Transforms image data index of image1 to index of image2
     *
     * @param i1     index of image1
     * @param width1 width of image1
     * @param width2 width of image2
     * @return index of image2 data with the same x,y coordinates as i1
     */
    public int i1ToI2(int i1, int width1, int width2) {
        int y = i1 / (width1 * channels);
        return i1 + (y * (width2 - width1) * channels);
    }
}
