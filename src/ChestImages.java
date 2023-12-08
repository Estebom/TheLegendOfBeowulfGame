import javax.swing.*;
import java.awt.*;

public class ChestImages extends JLabel {



    private int scaleWidth = 100;
    private int scaleHeight = 100;
    ImageIcon playerIcon;
    private static ImageIcon frontFacingChestClose;

    private static ImageIcon backFacingChestClose;
    private static ImageIcon leftFacingChestClose;
    private static ImageIcon rightFacingChestClose;


    private static ImageIcon frontFacingChestOpen;
    private static ImageIcon backFacingChestOpen;
    private static ImageIcon leftFacingChestOpen;
    private static ImageIcon rightFacingChestOpen;

    private ImageIcon originalIcon;

    private static PlayerImages instance;


    public ChestImages() {
        originalIcon = loadScaledIcon("src\\FRONTSTANDING.png");
        frontFacingChestClose = loadScaledIcon("src\\FrontFacingBeowulf.png");
        backFacingChestClose = loadScaledIcon("src\\FrontFacingRIGHTBeowulf.png");
        leftFacingChestClose = loadScaledIcon("src\\BackLeft.png");
        rightFacingChestClose = loadScaledIcon("src\\BackRight.png");
        frontFacingChestOpen = loadScaledIcon("src\\LeftFacingStanding.png");
        backFacingChestOpen = loadScaledIcon("src\\LeftFacingwalk.png");
        leftFacingChestOpen = loadScaledIcon("src\\RightFacingwalk.png");
        rightFacingChestOpen = loadScaledIcon("src\\RightFacingStanding.png");


        this.setIcon(originalIcon);
        this.setPreferredSize(new Dimension(scaleWidth, scaleHeight));
    }

    private ImageIcon loadScaledIcon(String path) {
        ImageIcon icon = new ImageIcon(path);
        Image scaledImage = icon.getImage().getScaledInstance(scaleWidth, scaleHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

}
