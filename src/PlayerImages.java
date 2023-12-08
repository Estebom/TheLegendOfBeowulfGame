import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
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
        originalIcon = loadScaledIcon("resources/images/FRONTSTANDING.png");
        frontFacingLeftIcon = loadScaledIcon("resources/images/FrontFacingBeowulf.png");
        frontFacingRightIcon = loadScaledIcon("resources/images/FrontFacingRIGHTBeowulf.png");
        backFacingLeftIcon = loadScaledIcon("resources/images/BackLeft.png");
        backFacingRightIcon = loadScaledIcon("resources/images/BackRight.png");
        leftFacingStillIcon = loadScaledIcon("resources/images/LeftFacingStanding.png");
        leftFacingWalkIcon = loadScaledIcon("resources/images/LeftFacingwalk.png");
        rightFacingWalkIcon = loadScaledIcon("resources/images/RightFacingwalk.png");
        rightFacingStillIcon = loadScaledIcon("resources/images/RightFacingStanding.png");

        leftFacingWalkIconSword = loadScaledIcon("resources/images/LeftFacingWalkSword.png");
        rightFacingWalkIconSword = loadScaledIcon("resources/images/RightFacingwalkSword.png");
        frontFacingWalkIconSword = loadScaledIcon("resources/images/FrontFacingBeowulfSwordRight.png");
        backLeftSword = loadScaledIcon("resources/images/BackLeftSword.png");
        // Set the initial icon
        this.setIcon(originalIcon);
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
