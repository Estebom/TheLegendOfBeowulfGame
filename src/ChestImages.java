import javax.swing.*;
import java.awt.*;

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

       // frontFacingChestClose = loadScaledIcon("src\\FrontFacingBeowulf.png");
        //backFacingChestClose = loadScaledIcon("src\\FrontFacingRIGHTBeowulf.png");
        leftFacingChestClose = loadScaledIcon("src\\leftFacingChestCLose.png");
        rightFacingChestClose = loadScaledIcon("src\\rightFacingChestClose.png");
        //frontFacingChestOpen = loadScaledIcon("src\\LeftFacingStanding.png");
        //backFacingChestOpen = loadScaledIcon("src\\LeftFacingwalk.png");
        leftFacingChestOpen = loadScaledIcon("src\\leftFacingChestOpen.png");
        rightFacingChestOpen = loadScaledIcon("src\\rightFacingChestOpen.png");

        this.setPreferredSize(new Dimension(scaleWidth, scaleHeight));
    }

    private ImageIcon loadScaledIcon(String path) {
        ImageIcon icon = new ImageIcon(path);
        Image scaledImage = icon.getImage().getScaledInstance(scaleWidth, scaleHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
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
