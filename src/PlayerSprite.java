import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PlayerSprite extends JLabel implements Movement{
    private int playerPosX = 750;
    private int playerPosY = 750;
    private static final int STEP = 5;
    ImageIcon playerIcon;
    private Image leftFacingStillScale,leftFacingWalkScale,rightFacingWalkScale,rightFacingStillScale;
    private Image frontFacingLeftScale, frontFacingRightScale, backFacingLeftScale, backFacingRightScale;
    private Timer animationTimer;
    private boolean isMoving = false;
    private char currentDirection = ' ';
    private boolean walkState = false;



    private static PlayerSprite instance;
    public static PlayerSprite getInstance(){

        if(instance == null){
            instance = new PlayerSprite();
        }
        return instance;
    }
    private PlayerSprite(){


        ImageIcon originalIcon = new ImageIcon("src\\genericSprite.png");
        ImageIcon frontFacingLeft = new ImageIcon("src\\FrontFacingBeowulf.png");
        this.frontFacingLeftScale = frontFacingLeft.getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH);
        ImageIcon frontFacingRight = new ImageIcon("src\\FrontFacingRIGHTBeowulf.png");
        this.frontFacingRightScale = frontFacingRight.getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH);

        ImageIcon backFacingLeft = new ImageIcon("src\\BackLeft.png");
        this.backFacingLeftScale = backFacingLeft.getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH);

        ImageIcon backFacingRight = new ImageIcon("src\\BackRight.png");
        this.backFacingRightScale = backFacingRight.getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH);


        ImageIcon leftFacingStill = new ImageIcon("src\\LeftFacingStanding.png");
        this.leftFacingStillScale = leftFacingStill.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon leftFacingWalk = new ImageIcon("src\\LeftFacingwalk.png");
        this.leftFacingWalkScale = leftFacingWalk.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon rightFacingWalk = new ImageIcon("src\\RightFacingwalk.png");
        this.rightFacingWalkScale = rightFacingWalk.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon rightFacingStill = new ImageIcon("src\\RightFacingStanding.png");
        this.rightFacingStillScale = rightFacingStill.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        //ImageIcon originalIcon = new ImageIcon("src\\genericSprite.png");







        // Scale the image to fit the desired width and height
        Image scaledImage = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);

        // Set the scaled image as the icon
        this.setIcon(new ImageIcon(scaledImage));

        // Optionally, set the label's size (not necessary if added to a layout manager)
        this.setPreferredSize(new Dimension(100, 100));

        animationTimer = new Timer(150, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateAnimation();
            }
        });

    }


    @Override
    public void move(char direction) {
        isMoving = true;
        currentDirection = direction;
        switch (direction){
            case 'w': playerPosY -= STEP;break;
            case 's': playerPosY += STEP;break;
            case 'a': playerPosX -= STEP;break;
            case 'd': playerPosX += STEP;break;
        }
        this.setLocation(playerPosX, playerPosY);
        if(!animationTimer.isRunning()){
            animationTimer.start();
        }
    }

    private void updateAnimation(){
        if(isMoving){
            walkState = !walkState;
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
                    this.setIcon(new ImageIcon(rightFacingWalkScale));
            }
            if(icon != null){
                this.setIcon(icon);
            }
        }
        else{
            animationTimer.stop();
        }
    }

    public void stopMoving(){
        isMoving = false;
        ImageIcon icon = null;
        switch (this.currentDirection) {
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
            this.setIcon(icon);
        }
    }
}
