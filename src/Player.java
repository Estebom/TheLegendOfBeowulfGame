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
            // Player Health and Damage
            instance.damageOutput = 1.0;
            instance.health = 100.0;

            instance.resetTimer();
        }
        return instance;
    }
    private Player(){
        damageOutput = 1.0;
        health = 100.0;

        resetTimer();
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
        animationTimer = new Timer(150, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateAnimation();
            }
        });
    }

    public static void move(char direction) {
        getInstance().isMoving = true;
        getInstance().currentDirection = direction;
        switch (direction){
            case 'w': getInstance().playerPosY -= STEP;break;
            case 's': getInstance().playerPosY += STEP;break;
            case 'a': getInstance().playerPosX -= STEP;break;
            case 'd': getInstance().playerPosX += STEP;break;
        }
        PlayerImages.getInstance().setLocation(getInstance().playerPosX, getInstance().playerPosY);
        if(!getInstance().animationTimer.isRunning()){
            getInstance().animationTimer.start();
        }
    }

    private void updateAnimation(){
        if(isMoving){
            walkState = !walkState;
            PlayerImages.getInstance().updateAnimation(currentDirection,walkState);
        }
        else{
            animationTimer.stop();
        }
    }

    public static void stopMoving(){
        getInstance().isMoving = false;
        PlayerImages.getInstance().stopMoving(getInstance().currentDirection);
    }

    public static void setPlayerName(String name){

        getInstance().playerName = name;
    }

    public static double getHealth(){

        return getInstance().health;

    }

    public static double getDamageOutput() {
        return getInstance().damageOutput;
    }

    public static void setDamageOutput(double damageOutput) {
        getInstance().damageOutput = damageOutput;
    }

    public static void setHealth(double damageIncoming){
        getInstance().health -= damageIncoming;
    }
    public static String getPlayerName(){
        return getInstance().playerName;
    }

    public static void replacePlayerInstance(Player newInstance){
        instance = newInstance;
        instance.resetTimer();
    }

    public static void setStarting(int x, int y){
        getInstance().playerPosX = x;
        getInstance().playerPosY = y;
    }

    public static int getPlayerPosX(){

        return getInstance().playerPosX;
    }
    public static int getPlayerPosY(){

        return getInstance().playerPosY;
    }
    public static void heal(double health){
        getInstance().health += health;
    }

    public static void takeDamage(double damage){
        getInstance().health -= damage;
    }
    public static char getCurrentDirection(){return getInstance().currentDirection;}



    public static void writeToOutputStream(ObjectOutputStream outputStream)throws java.io.IOException{
        outputStream.writeObject(getInstance());
    }
}
