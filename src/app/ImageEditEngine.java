package app;

import app.gui.imgegui.ImageGUI;
import app.constants.Messages;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageEditEngine {

    public static int[][][] originalImage;
    public static BufferedImage originalImageBuffer;
    public static File imageFile;
    private static FileDialog fileChooseDialog;

    public ImageEditEngine() {
        originalImage = new int[0][0][0];
        ImageGUI gui = new ImageGUI();
        gui.setVisible(true);
    }

    public static ImageIcon openImage(){
        try {
            String filePathFromDialog = fileChooseDialog.getFile();

            if(filePathFromDialog == null) {
                System.out.println(Messages.NO_FILE_CHOSEN);
                return null;
            }

            imageFile = new File(filePathFromDialog);
            originalImageBuffer = ImageIO.read(imageFile);
            return new ImageIcon(originalImageBuffer);
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }



    public static void openFileChooseDialog() {
        fileChooseDialog = new FileDialog(new JFrame(), "Wybierz obrazek");
        fileChooseDialog.setVisible(true);
    }
}
