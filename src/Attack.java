import java.io.Serializable;

/**
 * Esteban Rodriguez
 */
public  class Attack implements Serializable {
    private Enemy enemy;
    private GamePlay gamePlay;
    public Attack(GamePlay gamePlay) {
        this.gamePlay = gamePlay;
        if (this.gamePlay != null) {
            this.enemy = this.gamePlay.getCurrentTarget();
        }
    }
    public Attack(Enemy enemy) {
        this.enemy = enemy;
    }

public void playerNormalAttack(){
        this.enemy.takeDamage(Player.getDamageOutput());

}
public void enemyNormalAttack(){
    Player.takeDamage(enemy.getDamage());
}
public void playerSpecialAttack(){

        this.enemy.takeDamage(Player.getDamageOutput() * 1.30);

    }
    public void enemySpecialAttack(){
        Player.takeDamage(enemy.getDamage());
    }
}
