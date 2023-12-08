import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class EnemyImages extends JLabel{
        private int scaleWidth = 100;
        private int scaleHeight = 100;
        private ImageIcon frontFacingLeftIcon, frontFacingRightIcon;
        private ImageIcon backFacingLeftIcon, backFacingRightIcon;
        private ImageIcon leftFacingStillIcon, leftFacingWalkIcon;
        private ImageIcon rightFacingWalkIcon, rightFacingStillIcon;
        private ImageIcon originalIcon;



        public EnemyImages() {
            originalIcon = loadScaledIcon("resources/images/EnemiesFRONTSTANDING.png");
            frontFacingLeftIcon = loadScaledIcon("resources/images/EnemiesFrontFacingBeowulf_1.png");
            frontFacingRightIcon = loadScaledIcon("resources/images/EnemiesFrontFacingRIGHTBeowulf.png");
            backFacingLeftIcon = loadScaledIcon("resources/images/EnemiesBackLeft.png");
            backFacingRightIcon = loadScaledIcon("resources/images/EnemiesBackRight.png");
            leftFacingStillIcon = loadScaledIcon("resources/images/EnemiesLeftFacingStanding.png");
            leftFacingWalkIcon = loadScaledIcon("resources/images/EnemiesLeftFacingwalk.png");
            rightFacingWalkIcon = loadScaledIcon("resources/images/EnemiesRightFacingwalk.png");
            rightFacingStillIcon = loadScaledIcon("resources/images/EnemiesRightFacingStanding.png");

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
        public void updateAnimation(char currentDirection, boolean walkState) {
            ImageIcon icon = null;
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
            if (icon != null) {
                this.setIcon(icon);
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


