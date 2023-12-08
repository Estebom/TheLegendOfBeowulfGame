import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;
import java.io.Serializable;


/**
 * @author Iacopo Lenzi, Esteban Rodriguez
 */
public class Player extends JPanel implements Serializable, Movement {
    public String playerName;
    private double damageOutput;
    private double health;
    private int playerPosX = 500;
    private int playerPosY = 500;
    private static final int STEP = 5;

    private transient Timer animationTimer;
    private boolean isMoving = false;
    private boolean isAttacking = false;
    private boolean attackState = false;
    private char currentDirection = ' ';
    private boolean walkState = false;
    private boolean attackFinished = false;
    private static final int ATTACK_DURATION = 250;
    private Timer attackTimer;
    private JProgressBar healthBar;
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
        health = 1000.0;
        setupAttackTimer();



    }



    private void setupAttackTimer() {
        attackTimer = new Timer(ATTACK_DURATION, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finishAttack();
            }
        });
        attackTimer.setRepeats(false); // The timer should only run once per attack
    }
    public static void startAttack() {
        getInstance();
        instance.isAttacking = true;
        instance.attackTimer.start(); // Start the attack timer
        PlayerImages.updateAnimation(instance.currentDirection, instance.walkState, true);
    }
    private static void finishAttack() {
        getInstance();
        instance.isAttacking = false;
        instance.attackState = false; // Reset attack state
        PlayerImages.updateAnimation(instance.currentDirection, instance.walkState, false);
        if (!instance.isMoving) {
            instance.animationTimer.stop();
        }
    }

    public static void resetPlayerTimer() {
        getInstance().resetTimer();
    }
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
        updatePosition(direction); // Move this logic to a separate method
        PlayerImages.getInstance().setLocation(instance.playerPosX, instance.playerPosY);
        if (!instance.animationTimer.isRunning()) {
            instance.animationTimer.start();
        }
    }
    private static void updatePosition(char direction) {
        switch (direction) {
            case 'w': instance.playerPosY -= STEP; break;
            case 's': instance.playerPosY += STEP; break;
            case 'a': instance.playerPosX -= STEP; break;
            case 'd': instance.playerPosX += STEP; break;
        }
    }

    public void updateAnimation(){
        getInstance();
        if (isMoving) {
            walkState = !walkState;
            PlayerImages.updateAnimation(currentDirection, walkState, isAttacking);
        } else if (isAttacking) {
            attackState = !attackState;
            PlayerImages.updateAnimation(currentDirection, walkState, attackState);
            if (attackFinished) {
                isAttacking = false;
            }
        } else {
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
    public static void setAttacking(boolean b){
        getInstance();
        instance.attackState = b;
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
        SwingUtilities.invokeLater(() -> {
            getInstance();
            instance.health -= damage;

            // Ensure health does not go below 0
            instance.health = Math.max(instance.health, 0);

            // Update the health bar
            GamePlay.fill();
        });
    }
    private static void updateHealthBar() {
        getInstance();
        int healthValue = (int) Math.max(instance.health, 0); // Ensure health is not negative
        GamePlay.fill();
        instance.healthBar.setString("Health: " + healthValue);
    }
    public static char getCurrentDirection(){
        getInstance();
        return instance.currentDirection;}

    public static void setPlayerPosX(int posX){
        getInstance();
        instance.playerPosX = posX;

    }
    public static void setPlayerPosY(int posY){
        getInstance();
        instance.playerPosY = posY;
    }

    public static void writeToOutputStream(ObjectOutputStream outputStream)throws java.io.IOException{
        getInstance();
        outputStream.writeObject(instance);
    }
}
