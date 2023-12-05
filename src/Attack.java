public  class Attack {
    private Enemy enemy;
    private Player player;
    private GamePlay gamePlay;
    public Attack(Enemy enemy){

        this.enemy = enemy;


    }
public void playerNormalAttack(){
        this.enemy.takeDamage(player.getDamageOutput());

}
public void enemyNormalAttack(){
        player.takeDamage(enemy.getDamage());
}
public void playerSpecialAttack(){
        this.enemy.takeDamage(player.getDamageOutput()*1.30);
}
public void enemySpecialAttack(){
        player.takeDamage(enemy.getDamage());
}


}
