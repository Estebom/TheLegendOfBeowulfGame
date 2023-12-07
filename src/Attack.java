import javax.swing.*;
import java.io.Serializable;

/**
 * Esteban Rodriguez
 */
public  class Attack implements Serializable {
    private Enemy enemy;



    public Attack(Enemy enemy) {
        this.enemy = enemy;
    }

    public void playerNormalAttack() {

        this.enemy.takeDamage(Player.getDamageOutput());
        enemyKnockBack();
        java.lang.System.out.println("player attacked");
        java.lang.System.out.println(enemy.getHealth());

    }

    public void enemyNormalAttack() {
        Player.takeDamage(this.enemy.getDamage());
        playerKnockBack();
        //java.lang.System.out.println("attacking");
        java.lang.System.out.println(Player.getHealth());
    }

    public void playerSpecialAttack() {

        this.enemy.takeDamage(Player.getDamageOutput() * 1.70);

    }

    public void enemySpecialAttack() {
        Player.takeDamage(enemy.getDamage());
    }

    public void playerKnockBack() {


        switch (this.enemy.getCurrentDirection()) {
            case 'w':
                Player.setPlayerPosY(Player.getPlayerPosY() - 10);
                Player.move('w');
                break;
            case 'a':
                Player.setPlayerPosX(Player.getPlayerPosX() - 10);
                Player.move('a');
                break;
            case 's':
                Player.setPlayerPosY(Player.getPlayerPosY() + 10);
                Player.move('s');
                break;
            case 'd':
                Player.setPlayerPosX(Player.getPlayerPosX() + 10);
                Player.move('d');
                break;
        }

    }

    public void enemyKnockBack() {

        java.lang.System.out.println("Knockback called");

        char knockBackDirection = Player.getCurrentDirection();
        final int knockBackDistance = 10;

        this.enemy.suspendNormalMovement();
        SwingUtilities.invokeLater(() -> {
            // Update the enemy's position based on the knockback direction
            switch (knockBackDirection) {
                case 'w':
                    this.enemy.setPosy(this.enemy.getPosy() - knockBackDistance);
                    java.lang.System.out.println("knocking back");
                    break;
                case 'a':
                    this.enemy.setPosx(this.enemy.getPosx() - knockBackDistance);
                    java.lang.System.out.println("knocking back");
                    break;
                case 's':
                    this.enemy.setPosy(this.enemy.getPosy() + knockBackDistance);
                    java.lang.System.out.println("knocking back");
                    break;
                case 'd':
                    this.enemy.setPosx(this.enemy.getPosx() + knockBackDistance);
                    java.lang.System.out.println("knocking back");
                    break;

            }
            this.enemy.setLocation(this.enemy.getPosx(), this.enemy.getPosy());
            if (this.enemy.getParent() != null) {
                this.enemy.getParent().repaint();
            }
            this.enemy.resumeNormalMovementAfterDelay();
            java.lang.System.out.println("New Position - X: " + enemy.getPosx() + ", Y: " + enemy.getPosy());


        });
    }

    public void setCurrentTarget(Enemy currentTarget){
        this.enemy = currentTarget;
    }
}