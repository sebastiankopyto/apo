import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageEditEngine {

    private static int[][][] originalImage;
    private static BufferedImage originalImageBuffer;
    private static FileDialog fd;

    public ImageEditEngine() {
        originalImage = new int[0][0][0];
        ImageGUI gui = new ImageGUI();
        gui.setVisible(true);
    }

    public static ImageIcon openImage(){
        try {
            BufferedImage image = ImageIO.read(new File(fd.getFile()));
            originalImageBuffer = image;
            ImageIcon icon = new ImageIcon(image);
            return icon;

        }
        catch(Exception e){
            e.printStackTrace();
            return new ImageIcon();
        }
    }

    public static void fileDialog() {
        fd = new FileDialog(new JFrame(), "Wybierz obrazek");
        fd.setVisible(true);
    }

}
