import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class PlayerImages extends JLabel {
    private int scaleWidth = 100;
    private int scaleHeight = 100;
    ImageIcon playerIcon;
    private Image leftFacingStillScale, leftFacingWalkScale, rightFacingWalkScale, rightFacingStillScale;
    private Image frontFacingLeftScale, frontFacingRightScale, backFacingLeftScale, backFacingRightScale;
    private static PlayerImages instance;

    public static PlayerImages getInstance() {

        if (instance == null) {
            instance = new PlayerImages();
        }
        return instance;
    }

    private PlayerImages() {
        ImageIcon originalIcon = new ImageIcon("src\\FRONTSTANDING.png");
        ImageIcon frontFacingLeft = new ImageIcon("src\\FrontFacingBeowulf.png");
        this.frontFacingLeftScale = frontFacingLeft.getImage().getScaledInstance(scaleWidth, scaleHeight, Image.SCALE_SMOOTH);
        ImageIcon frontFacingRight = new ImageIcon("src\\FrontFacingRIGHTBeowulf.png");
        this.frontFacingRightScale = frontFacingRight.getImage().getScaledInstance(scaleWidth, scaleHeight, Image.SCALE_SMOOTH);

        ImageIcon backFacingLeft = new ImageIcon("src\\BackLeft.png");
        this.backFacingLeftScale = backFacingLeft.getImage().getScaledInstance(scaleWidth, scaleHeight, Image.SCALE_SMOOTH);

        ImageIcon backFacingRight = new ImageIcon("src\\BackRight.png");
        this.backFacingRightScale = backFacingRight.getImage().getScaledInstance(scaleWidth, scaleHeight, Image.SCALE_SMOOTH);


        ImageIcon leftFacingStill = new ImageIcon("src\\LeftFacingStanding.png");
        this.leftFacingStillScale = leftFacingStill.getImage().getScaledInstance(scaleWidth, scaleHeight, Image.SCALE_SMOOTH);
        ImageIcon leftFacingWalk = new ImageIcon("src\\LeftFacingwalk.png");
        this.leftFacingWalkScale = leftFacingWalk.getImage().getScaledInstance(scaleWidth, scaleHeight, Image.SCALE_SMOOTH);
        ImageIcon rightFacingWalk = new ImageIcon("src\\RightFacingwalk.png");
        this.rightFacingWalkScale = rightFacingWalk.getImage().getScaledInstance(scaleWidth, scaleHeight, Image.SCALE_SMOOTH);
        ImageIcon rightFacingStill = new ImageIcon("src\\RightFacingStanding.png");
        this.rightFacingStillScale = rightFacingStill.getImage().getScaledInstance(scaleWidth, scaleHeight, Image.SCALE_SMOOTH);
        //ImageIcon originalIcon = new ImageIcon("src\\genericSprite.png");

        // Scale the image to fit the desired width and height
        Image scaledImage = originalIcon.getImage().getScaledInstance(scaleWidth, scaleHeight, Image.SCALE_SMOOTH);

        // Set the scaled image as the icon
        this.setIcon(new ImageIcon(scaledImage));

        // Optionally, set the label's size (not necessary if added to a layout manager)
        this.setPreferredSize(new Dimension(scaleWidth, scaleHeight));
    }

    public void updateAnimation(char currentDirection, boolean walkState){
        ImageIcon icon = null;
        switch (currentDirection){
            case 'w':
                icon = walkState ? new ImageIcon(backFacingLeftScale) : new ImageIcon(backFacingRightScale);
                break;
            case 's':
                icon = walkState ? new ImageIcon(frontFacingLeftScale): new ImageIcon(frontFacingRightScale);
                break;
            case 'a':
                icon = walkState ? new ImageIcon(leftFacingWalkScale) : new ImageIcon(leftFacingStillScale);
                break;
            case 'd':
                icon = walkState ? new ImageIcon(rightFacingWalkScale) : new ImageIcon(rightFacingStillScale);
//                getInstance().setIcon(new ImageIcon(rightFacingWalkScale));
                break;
        }
        if(icon != null){
            getInstance().setIcon(icon);
        }
    }

    public void stopMoving(char currentDirection){
        ImageIcon icon = null;
        switch (currentDirection) {
            case 'w':
                icon = new ImageIcon(backFacingRightScale);
                break;
            case 's':
                icon = new ImageIcon(frontFacingRightScale);
                break;
            case 'a':
                icon = new ImageIcon(leftFacingStillScale);
                break;
            case 'd':
                icon = new ImageIcon(rightFacingStillScale);
                break;
        }
        if(icon != null){
            getInstance().setIcon(icon);
        }
    }

}
