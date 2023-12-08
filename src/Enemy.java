import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

/**
 * @author Esteban Rodriguez
 */
public class Enemy extends EnemyImages implements Serializable , Movement {

    private Attack attack;
    private double health = 100;
    private int damage = 5;
    private int speed;
    private int drop;
    private String name;
    private transient Timer animationTimer;
    private boolean isMoving = false;
    private boolean walkState = false;
    private volatile boolean active = true;
    private volatile boolean attacking = false;
    private boolean inKnockback = false;
    private int posx;
    private int posy;
    private int step = 5;
    private char currentDirection = ' ';
    private Collectable[] collectable = new Collectable[5];

    public Enemy(String name) {
        this.name = name;
        this.drop = (int) (Math.random() * 5) - 1;
        this.attack = new Attack(this);
        this.resetTimer();
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
    public void setStep(int step){
        this.step = step;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getHealth() {
        return this.health;
    }

    public int getDamage() {
        return this.damage;
    }

    public char getCurrentDirection(){return this.currentDirection;}

    public int getSpeed() {
        return this.speed;
    }

    public void addColectable(int i, Collectable collectable) {
        this.collectable[i] = collectable;
    }

    public Collectable getCollectable(int i) {
        return collectable[i];
    }

    public void heal(double health) {
        this.health += health;
    }

    public void takeDamage(double damage) {
        this.health -= damage;
    }

    public void death() {
        getCollectable(this.drop);
    }

    public int getPosx() {
        return this.posx;
    }

    public int getPosy() {
        return this.posy;
    }
    public void setPosx(int posx){this.posx = posx;}
    public void setPosy(int posy){this.posy = posy;}
    public void suspendNormalMovement() {
        inKnockback = true;
        java.lang.System.out.println("Movement suspended");

    }
    public void resumeNormalMovementAfterDelay() {
        int delay = 500;
        Timer resumeTimer = new Timer(delay, e -> {
            inKnockback = false;
            java.lang.System.out.println("Movement resumed");
            ((Timer)e.getSource()).stop(); // Stop the timer after execution
        });
        resumeTimer.setRepeats(false); // Ensure the timer only runs once
        resumeTimer.start();
    }

    public void setPosition(int posx, int posy) {
        this.posx = posx;
        this.posy = posy;
    }

    private void resetTimer() {

        this.animationTimer = new Timer(150, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateAnimation();
            }
        });
    }

    public void move(char direction) {
        this.isMoving = true;
        this.currentDirection = direction;
        updatePosition(direction); // Move this logic to a separate method

        this.setLocation(this.posx, this.posy);
        if (!this.animationTimer.isRunning()) {
            this.animationTimer.start();
        }
    }

    private void updatePosition(char direction) {
        switch (direction) {
            case 'w':
                this.posy -= this.step;
                java.lang.System.out.println("w");
                break;

            case 's':
                this.posy += this.step;
                java.lang.System.out.println("s");
                break;
            case 'a':
                this.posx -= this.step;
                java.lang.System.out.println("a");
                break;
            case 'd':
                this.posx += this.step;
                java.lang.System.out.println("d");
                break;
        }
        SwingUtilities.invokeLater(() -> {
            this.setBounds(this.posx, this.posy, this.getWidth(), this.getHeight());
            if (this.getParent() != null) {
                this.getParent().repaint();
            }
        });
        java.lang.System.out.println("Updated Position in move - X: " + this.posx + ", Y: " + this.posy);
    }

    private void updateAnimation() {

        if (this.isMoving) {
            this.walkState = !walkState;
            super.updateAnimation(this.currentDirection, this.walkState);
        } else {
            this.animationTimer.stop();
        }
    }

    public void stopMoving() {

        this.isMoving = false;
        super.stopMoving(this.currentDirection);
    }
    public void startBehavior() {
        findPlayer(true);
        isMoving = true;
    }

    public void stopBehavior(){
        findPlayer(false);
        isMoving = false;

    }
    private void findPlayer(boolean behave) {
        if (behave) {
            active = true;
            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

                @Override
                protected Void doInBackground() throws Exception {
                    while (active) {
                        int x = Player.getPlayerPosX();
                        int y = Player.getPlayerPosY();
                        if (Player.getHealth() <=0 || Enemy.this.getHealth() <= 0){
                            stopBehavior();
                            break;
                        }
                        if (!inKnockback) {
                            // Check if the player is within attack range
                            if ((Math.abs(x - posx) <= 25) && (Math.abs(y - posy) <= 25)) {
                                // Player is within attack range
                                SwingUtilities.invokeLater(() -> attack.enemyNormalAttack());
                            } else {
                                // Player is out of attack range, update the enemy's position
                                char direction = determineDirection(x, y);
                                SwingUtilities.invokeLater(() -> move(direction));
                            }
                        }
                        Thread.sleep(100); // Control the rate of checking and attacking
                    }
                    return null;
                }

                private char determineDirection(int playerX, int playerY) {
                    // Implement logic to determine direction based on player position
                    if (Math.abs(playerX - posx) > Math.abs(playerY - posy)) {
                        return (playerX > posx) ? 'd' : 'a'; // Move right or left
                    } else {
                        return (playerY > posy) ? 's' : 'w'; // Move down or up
                    }
                }
            };
            worker.execute();
        } else {
            active = false; // Deactivate the tracking and attacking
        }
    }
}

