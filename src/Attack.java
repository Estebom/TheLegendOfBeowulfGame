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

        enemy.suspendNormalMovement();
        SwingUtilities.invokeLater(() -> {
            // Update the enemy's position based on the knockback direction
            switch (knockBackDirection) {
                case 'w':
                    enemy.setPosy(enemy.getPosy() - 10);
//                    enemy.move('w');
                    break;
                case 'a':
                    enemy.setPosx(enemy.getPosx() - 10);
//                    enemy.move('a');
                    break;
                case 's':
                    enemy.setPosy(enemy.getPosy() + 10);
//                    enemy.move('s');
                    break;
                case 'd':
                    enemy.setPosx(enemy.getPosx() + 10);
//                    enemy.move('d');
                    break;

            }
            enemy.setLocation(enemy.getPosx(),enemy.getPosy());
            if (enemy.getParent() != null) {
                enemy.getParent().revalidate();
                enemy.getParent().repaint();
            }
            enemy.resumeNormalMovementAfterDelay();
            java.lang.System.out.println("New Position - X: " + enemy.getPosx() + ", Y: " + enemy.getPosy());


        });
    }

    public void setCurrentTarget(Enemy currentTarget){
        this.enemy = currentTarget;
    }
}