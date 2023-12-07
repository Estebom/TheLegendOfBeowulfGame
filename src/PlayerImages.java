import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class PlayerImages extends JLabel {
    private int scaleWidth = 100;
    private int scaleHeight = 100;
    ImageIcon playerIcon;
    private static ImageIcon frontFacingLeftIcon;
    private static ImageIcon frontFacingRightIcon;
    private static ImageIcon backFacingLeftIcon;
    private static ImageIcon backFacingRightIcon;
    private static ImageIcon leftFacingStillIcon;
    private static ImageIcon leftFacingWalkIcon;
    private static ImageIcon rightFacingWalkIcon;
    private static ImageIcon rightFacingStillIcon;
    private ImageIcon originalIcon;
    private static ImageIcon leftFacingWalkIconSword;
    private static ImageIcon rightFacingWalkIconSword;
    private static ImageIcon frontFacingWalkIconSword;
    private static ImageIcon backLeftSword;
    private static PlayerImages instance;

    public static PlayerImages getInstance() {

        if (instance == null) {
            instance = new PlayerImages();
        }
        return instance;
    }

    private PlayerImages() {
        originalIcon = loadScaledIcon("src\\FRONTSTANDING.png");
        frontFacingLeftIcon = loadScaledIcon("src\\FrontFacingBeowulf.png");
        frontFacingRightIcon = loadScaledIcon("src\\FrontFacingRIGHTBeowulf.png");
        backFacingLeftIcon = loadScaledIcon("src\\BackLeft.png");
        backFacingRightIcon = loadScaledIcon("src\\BackRight.png");
        leftFacingStillIcon = loadScaledIcon("src\\LeftFacingStanding.png");
        leftFacingWalkIcon = loadScaledIcon("src\\LeftFacingwalk.png");
        rightFacingWalkIcon = loadScaledIcon("src\\RightFacingwalk.png");
        rightFacingStillIcon = loadScaledIcon("src\\RightFacingStanding.png");

        leftFacingWalkIconSword = loadScaledIcon("src\\LeftFacingWalkSword.png");
        rightFacingWalkIconSword = loadScaledIcon("src\\RightFacingwalkSword.png");
        frontFacingWalkIconSword = loadScaledIcon("src\\FrontFacingBeowulfSwordRight.png");
        backLeftSword = loadScaledIcon("src\\BackLeftSword.png");
        // Set the initial icon
        this.setIcon(originalIcon);
        this.setPreferredSize(new Dimension(scaleWidth, scaleHeight));
    }

    private ImageIcon loadScaledIcon(String path) {
        ImageIcon icon = new ImageIcon(path);
        Image scaledImage = icon.getImage().getScaledInstance(scaleWidth, scaleHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
    public static void updateAnimation(char currentDirection, boolean walkState, boolean attackState) {
        ImageIcon icon = null;
        if (attackState) {
            switch (currentDirection) {
                case 'a': icon = leftFacingWalkIconSword;
                    break;
                case 'd': icon = rightFacingWalkIconSword;
                    break;
                case 'w': icon = backLeftSword;
                    break;
                case 's': icon = frontFacingWalkIconSword;
                    break;
            }
        } else {
            switch (currentDirection) {
                case 'w':
                    icon = walkState ? backFacingLeftIcon : backFacingRightIcon;
                    break;
                case 's':
                    icon = walkState ? frontFacingLeftIcon : frontFacingRightIcon;
                    break;
                case 'a':
                    icon = walkState ? leftFacingWalkIcon : leftFacingStillIcon;
                    break;
                case 'd':
                    icon = walkState ? rightFacingWalkIcon : rightFacingStillIcon;
                    break;
            }
        }
        if (icon != null) {
            instance.setIcon(icon);
        }
    }



    public void stopMoving(char currentDirection) {
        ImageIcon icon = null;
        switch (currentDirection) {
            case 'w':
                icon = backFacingRightIcon;
                break;
            case 's':
                icon = frontFacingRightIcon;
                break;
            case 'a':
                icon = leftFacingStillIcon;
                break;
            case 'd':
                icon = rightFacingStillIcon;
                break;
        }
        if (icon != null) {
            this.setIcon(icon);
        }
    }

}
