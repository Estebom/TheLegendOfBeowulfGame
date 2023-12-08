import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Player extends JPanel implements Serializable, Movement {
    public String playerName;
    private double damageOutput = 1.0;
    private double health = 1000.0;
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

    private static Player instance;

    private static Player getInstance() {
        if (instance == null) {
            instance = new Player();
            instance.resetTimer();
        }

        return instance;
    }

    private Player() {
        this.setupAttackTimer();
    }

    private void setupAttackTimer() {
        this.attackTimer = new Timer(250, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Player.finishAttack();
            }
        });
        this.attackTimer.setRepeats(false);
    }

    public static void startAttack() {
        getInstance();
        instance.isAttacking = true;
        instance.attackTimer.start();
        PlayerImages.updateAnimation(instance.currentDirection, instance.walkState, true);
    }

    private static void finishAttack() {
        getInstance();
        instance.isAttacking = false;
        instance.attackState = false;
        PlayerImages.updateAnimation(instance.currentDirection, instance.walkState, false);
        if (!instance.isMoving) {
            instance.animationTimer.stop();
        }

    }

    public static void resetPlayerTimer() {
        getInstance().resetTimer();
    }

    private void resetTimer() {
        getInstance();
        this.animationTimer = new Timer(150, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Player.this.updateAnimation();
            }
        });
    }

    public static void move(char direction) {
        getInstance();
        instance.isMoving = true;
        instance.currentDirection = direction;
        updatePosition(direction);
        PlayerImages.getInstance().setLocation(instance.playerPosX, instance.playerPosY);
        if (!instance.animationTimer.isRunning()) {
            instance.animationTimer.start();
        }

    }

    private static void updatePosition(char direction) {
        Player var10000;
        switch (direction) {
            case 'a':
                var10000 = instance;
                var10000.playerPosX -= 5;
                break;
            case 'd':
                var10000 = instance;
                var10000.playerPosX += 5;
                break;
            case 's':
                var10000 = instance;
                var10000.playerPosY += 5;
                break;
            case 'w':
                var10000 = instance;
                var10000.playerPosY -= 5;
        }

    }

    public void updateAnimation() {
        getInstance();
        if (this.isMoving) {
            this.walkState = !this.walkState;
            PlayerImages.updateAnimation(this.currentDirection, this.walkState, this.isAttacking);
        } else if (this.isAttacking) {
            this.attackState = !this.attackState;
            PlayerImages.updateAnimation(this.currentDirection, this.walkState, this.attackState);
            if (this.attackFinished) {
                this.isAttacking = false;
            }
        } else {
            this.animationTimer.stop();
        }

    }

    public static void stopMoving() {
        getInstance();
        instance.isMoving = false;
        PlayerImages.getInstance().stopMoving(instance.currentDirection);
    }

    public static void setPlayerName(String name) {
        getInstance();
        instance.playerName = name;
    }

    public static double getHealth() {
        getInstance();
        return instance.health;
    }

    public static void setAttacking(boolean b) {
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

    public static void setHealth(double damageIncoming) {
        getInstance();
        Player var10000 = instance;
        var10000.health -= damageIncoming;
    }

    public static String getPlayerName() {
        getInstance();
        return instance.playerName;
    }

    public static void replacePlayerInstance(Player newInstance) {
        instance = newInstance;
        instance.resetTimer();
    }

    public static void setStarting(int x, int y) {
        getInstance();
        instance.playerPosX = x;
        instance.playerPosY = y;
    }

    public static int getPlayerPosX() {
        getInstance();
        return instance.playerPosX;
    }

    public static int getPlayerPosY() {
        getInstance();
        return instance.playerPosY;
    }

    public static void heal(double health) {
        getInstance();
        Player var10000 = instance;
        var10000.health += health;
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




    public static char getCurrentDirection() {
        getInstance();
        return instance.currentDirection;
    }

    public static void setPlayerPosX(int posX) {
        getInstance();
        instance.playerPosX = posX;
    }

    public static void setPlayerPosY(int posY) {
        getInstance();
        instance.playerPosY = posY;
    }

    public static void writeToOutputStream(ObjectOutputStream outputStream) throws IOException {
        getInstance();
        outputStream.writeObject(instance);
    }
}
