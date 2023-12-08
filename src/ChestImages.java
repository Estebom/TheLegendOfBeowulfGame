import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class ChestImages extends JLabel {
    private int scaleWidth = 100;
    private int scaleHeight = 100;
    ImageIcon playerIcon;
    public static ImageIcon frontFacingChestClose;
    public static ImageIcon backFacingChestClose;
    public static ImageIcon leftFacingChestClose;
    public static ImageIcon rightFacingChestClose;
    private static ImageIcon frontFacingChestOpen;
    private static ImageIcon backFacingChestOpen;
    public static ImageIcon leftFacingChestOpen;
    public static ImageIcon rightFacingChestOpen;
    private ImageIcon originalIcon;
    private static PlayerImages instance;
    public ChestImages() {
        // frontFacingChestClose = loadScaledIcon("resources/images/FrontFacingBeowulf.png");
        //backFacingChestClose = loadScaledIcon("resources/images/FrontFacingRIGHTBeowulf.png");
        leftFacingChestClose = loadScaledIcon("resources/images/leftFacingChestClose.png");
        rightFacingChestClose = loadScaledIcon("resources/images/rightFacingChestClose.png");
        //frontFacingChestOpen = loadScaledIcon("resources/images/LeftFacingStanding.png");
        //backFacingChestOpen = loadScaledIcon("resources/images/LeftFacingwalk.png");
        leftFacingChestOpen = loadScaledIcon("resources/images/leftFacingChestOpen.png");
        rightFacingChestOpen = loadScaledIcon("resources/images/rightFacingChestOpen.png");
        this.setPreferredSize(new Dimension(scaleWidth, scaleHeight));
    }
    private ImageIcon loadScaledIcon(String path) {
        BufferedImage img = null;
        try (InputStream is = getClass().getResourceAsStream(path)) {
            if (is != null) {
                img = ImageIO.read(is);
            } else {
                // Consider logging this or throwing an exception
                System.err.println("Could not load image at path: " + path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (img != null) {
            Image scaledImage = img.getScaledInstance(scaleWidth, scaleHeight, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        } else {
            return new ImageIcon(); // Return an empty icon in case of failure
        }
    }
    public  void updateAnimation(char currentDirection, boolean closedState) {
        ImageIcon icon = null;
        switch (currentDirection) {
            case 'a':
                icon = closedState ? leftFacingChestClose : leftFacingChestOpen;
                break;
            case 'd':
                icon = closedState ? rightFacingChestClose : rightFacingChestOpen;
                break;
        }
//                case 'w': icon = backLeftSword;
//                    break;
//                case 's': icon = frontFacingWalkIconSword;
//                    break;

        if (icon != null) {
            this.setIcon(icon);
            this.repaint();
            this.revalidate();
        }
    }
}