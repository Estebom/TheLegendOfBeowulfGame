import java.io.Serializable;

/**
 * Esteban Rodriguez
 */
public  class Attack implements Serializable {
    private Enemy enemy;

    public Attack() {
        this.enemy = GamePlay.getCurrentTarget();

    }
    public Attack(Enemy enemy) {
        this.enemy = enemy;
    }

public void playerNormalAttack(){
        this.enemy.takeDamage(Player.getDamageOutput());
        java.lang.System.out.println("player attacked");
        java.lang.System.out.println(enemy.getHealth());
}
public void enemyNormalAttack(){
    Player.takeDamage(this.enemy.getDamage());
    //java.lang.System.out.println("attacking");
    java.lang.System.out.println(Player.getHealth());
}
public void playerSpecialAttack(){

        this.enemy.takeDamage(Player.getDamageOutput() * 1.70);

    }
    public void enemySpecialAttack(){
        Player.takeDamage(enemy.getDamage());
    }
}
