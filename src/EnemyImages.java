import javax.swing.*;
import java.awt.*;

public class EnemyImages extends JLabel{
        private int scaleWidth = 100;
        private int scaleHeight = 100;
        private ImageIcon frontFacingLeftIcon, frontFacingRightIcon;
        private ImageIcon backFacingLeftIcon, backFacingRightIcon;
        private ImageIcon leftFacingStillIcon, leftFacingWalkIcon;
        private ImageIcon rightFacingWalkIcon, rightFacingStillIcon;
        private ImageIcon originalIcon;



        public EnemyImages() {
            originalIcon = loadScaledIcon("src\\EnemiesFRONTSTANDING.png");
            frontFacingLeftIcon = loadScaledIcon("src\\EnemiesFrontFacingBeowulf_1.png");
            frontFacingRightIcon = loadScaledIcon("src\\EnemiesFrontFacingRIGHTBeowulf.png");
            backFacingLeftIcon = loadScaledIcon("src\\EnemiesBackLeft.png");
            backFacingRightIcon = loadScaledIcon("src\\EnemiesBackRight.png");
            leftFacingStillIcon = loadScaledIcon("src\\EnemiesLeftFacingStanding.png");
            leftFacingWalkIcon = loadScaledIcon("src\\EnemiesLeftFacingwalk.png");
            rightFacingWalkIcon = loadScaledIcon("src\\EnemiesRightFacingwalk.png");
            rightFacingStillIcon = loadScaledIcon("src\\EnemiesRightFacingStanding.png");

            // Set the initial icon
            this.setIcon(originalIcon);
            this.setPreferredSize(new Dimension(scaleWidth, scaleHeight));
        }

        private ImageIcon loadScaledIcon(String path) {
            ImageIcon icon = new ImageIcon(path);
            Image scaledImage = icon.getImage().getScaledInstance(scaleWidth, scaleHeight, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
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


