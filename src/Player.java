import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;
import java.io.Serializable;



public class Player implements Serializable {
    public String playerName;
    private double damageOutput;

    private double health;
    private int playerPosX;
    private int playerPosY;
    private static final int STEP = 5;

    private transient Timer animationTimer;
    private boolean isMoving = false;
    private char currentDirection = ' ';
    private boolean walkState = false;


    private static Player instance;
    private static Player getInstance(){

        if(instance == null){
            instance = new Player();
            instance.resetTimer();
        }
        return instance;
    }
    private Player(){
        damageOutput = 1.0;
        health = 100.0;


    }
//=======
//        ImageIcon spawn = new ImageIcon("src\\FRONTSTANDING.png");
//        ImageIcon frontFacingLeft = new ImageIcon("src\\FrontFacingBeowulf.png");
//        this.frontFacingLeftScale = frontFacingLeft.getImage().getScaledInstance(scaleWidth,scaleHeight,Image.SCALE_SMOOTH);
//        ImageIcon frontFacingRight = new ImageIcon("src\\FrontFacingRIGHTBeowulf.png");
//        this.frontFacingRightScale = frontFacingRight.getImage().getScaledInstance(scaleWidth,scaleHeight,Image.SCALE_SMOOTH);
//
//        ImageIcon backFacingLeft = new ImageIcon("src\\BackLeft.png");
//        this.backFacingLeftScale = backFacingLeft.getImage().getScaledInstance(scaleWidth,scaleHeight,Image.SCALE_SMOOTH);
//
//        ImageIcon backFacingRight = new ImageIcon("src\\BackRight.png");
//        this.backFacingRightScale = backFacingRight.getImage().getScaledInstance(scaleWidth,scaleHeight,Image.SCALE_SMOOTH);
//
//
//        ImageIcon leftFacingStill = new ImageIcon("src\\LeftFacingStanding.png");
//        this.leftFacingStillScale = leftFacingStill.getImage().getScaledInstance(scaleWidth, scaleHeight, Image.SCALE_SMOOTH);
//        ImageIcon leftFacingWalk = new ImageIcon("src\\LeftFacingwalk.png");
//        this.leftFacingWalkScale = leftFacingWalk.getImage().getScaledInstance(scaleWidth, scaleHeight, Image.SCALE_SMOOTH);
//        ImageIcon rightFacingWalk = new ImageIcon("src\\RightFacingwalk.png");
//        this.rightFacingWalkScale = rightFacingWalk.getImage().getScaledInstance(scaleWidth, scaleHeight, Image.SCALE_SMOOTH);
//        ImageIcon rightFacingStill = new ImageIcon("src\\RightFacingStanding.png");
//        this.rightFacingStillScale = rightFacingStill.getImage().getScaledInstance(scaleWidth, scaleHeight, Image.SCALE_SMOOTH);
//        //ImageIcon originalIcon = new ImageIcon("src\\genericSprite.png");
//
//
//
//
//
//
//
//        // Scale the image to fit the desired width and height
//        Image scaledImage = spawn.getImage().getScaledInstance(scaleWidth, scaleHeight, Image.SCALE_SMOOTH);
//
//        // Set the scaled image as the icon
//        this.setIcon(new ImageIcon(scaledImage));
//
//        // Optionally, set the label's size (not necessary if added to a layout manager)
//        this.setPreferredSize(new Dimension(scaleWidth, scaleHeight));
//>>>>>>> d487c83f6848111594173783db378950bfb69473

    private void resetTimer(){
        getInstance();
        animationTimer = new Timer(150, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateAnimation();
            }
        });
    }

    public static void move(char direction) {
        getInstance();
        instance.isMoving = true;
        instance.currentDirection = direction;
        switch (direction){
            case 'w': instance.playerPosY -= STEP;break;
            case 's': instance.playerPosY += STEP;break;
            case 'a': instance.playerPosX -= STEP;break;
            case 'd': instance.playerPosX += STEP;break;
        }
        PlayerImages.getInstance().setLocation(instance.playerPosX, instance.playerPosY);
        if(!instance.animationTimer.isRunning()){
            instance.animationTimer.start();
        }
    }

    private void updateAnimation(){
        getInstance();
        if(isMoving){
            walkState = !walkState;
            PlayerImages.getInstance().updateAnimation(currentDirection,walkState);
        }
        else{
            animationTimer.stop();
        }
    }

    public static void stopMoving(){
        getInstance();
        instance.isMoving = false;
        PlayerImages.getInstance().stopMoving(instance.currentDirection);
    }

    public static void setPlayerName(String name){
        getInstance();

        instance.playerName = name;
    }

    public static double getHealth(){
        getInstance();

        return instance.health;

    }

    public static double getDamageOutput() {
        getInstance();
        return instance.damageOutput;
    }

    public static void setDamageOutput(double damageOutput) {
        getInstance();
        instance.damageOutput = damageOutput;
    }

    public static void setHealth(double damageIncoming){
        getInstance();
        instance.health -= damageIncoming;
    }
    public static String getPlayerName(){
        getInstance();
        return instance.playerName;
    }

    public static void replacePlayerInstance(Player newInstance){
        instance = newInstance;
        instance.resetTimer();
    }

    public static void setStarting(int x, int y){
        getInstance();
        instance.playerPosX = x;
        instance.playerPosY = y;
    }

    public static int getPlayerPosX(){
        getInstance();

        return instance.playerPosX;
    }
    public static int getPlayerPosY(){
        getInstance();


        return instance.playerPosY;
    }
    public static void heal(double health){
        getInstance();
        instance.health += health;
    }

    public static void takeDamage(double damage){
        getInstance();
        instance.health -= damage;
    }
    public static char getCurrentDirection(){
        getInstance();
        return instance.currentDirection;}



    public static void writeToOutputStream(ObjectOutputStream outputStream)throws java.io.IOException{
        getInstance();
        outputStream.writeObject(instance);
    }
}
