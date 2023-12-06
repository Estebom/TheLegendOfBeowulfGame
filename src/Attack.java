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
        this.enemy.takeDamage(PlayerSprite.getDamageOutput());

}
public void enemyNormalAttack(){
        PlayerSprite.takeDamage(enemy.getDamage());
}
public void playerSpecialAttack(){

        this.enemy.takeDamage(PlayerSprite.getDamageOutput() * 1.30);

    }
    public void enemySpecialAttack(){
        PlayerSprite.takeDamage(enemy.getDamage());
    }
}
