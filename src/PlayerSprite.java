import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;


public class PlayerSprite implements Movement, Serializable {
    private String playerName;
    private double damageOutput;

    private double health;
    private int playerPosX;
    private int playerPosY;
    private static final int STEP = 5;

    private transient Timer animationTimer;
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

        // Player Health and Damage
        this.damageOutput = 1.0;
        this.health = 100.0;

        resetTimer();
    }

    private void resetTimer(){
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
        if(!animationTimer.isRunning()){
            animationTimer.start();
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

    public void stopMoving(){
        isMoving = false;
        PlayerImages.getInstance().stopMoving(currentDirection);
    }

    public static void setPlayerName(String name){

        getInstance().playerName = name;
    }

    public double getHealth(){

        return health;

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

    public static void replacePlayerInstance(PlayerSprite newInstance){             //Edit by Nohea
        instance = newInstance;
        instance.resetTimer();
    }

    public void setStarting(int x, int y){
        getInstance().playerPosX = x;
        getInstance().playerPosY = y;
    }

    public int getPlayerPosX(){

        return getInstance().playerPosX;
    }
    public int getPlayerPosY(){

        return getInstance().playerPosY;
    }

}
