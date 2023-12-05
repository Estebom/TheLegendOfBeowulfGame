import java.util.ArrayList;
import java.util.List;

/**
 * @author Esteban Rodriguez
 */
public class Enemy {
    private Attack attack;

    private double health;
    private int damage;

    private int speed;

    private int drop;
    private String name;

    private int posx;
    private int posy;
    private int step = 5;
    private char currentDirection = ' ';

    private Collectable[] collectable = new Collectable[5];

    public Enemy(String name) {
        this.name = name;
        this.drop = (int) (Math.random() * 5) - 1;
        this.attack = new Attack(this);
    }

    public void setDamage(int damage) {
        this.damage = damage;
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
    public int getPosx(){return this.posx;}
    public int getPosy(){return this.posy;}

    public void findPlayer() {

        while (Player.getInstance().getHealth() != 0) {
            int x = PlayerSprite.getInstance().getPlayerPosX();
            int y = PlayerSprite.getInstance().getPlayerPosY();
            if ((Math.abs(x - posx) <= 10) || (Math.abs(x - posy) <= 10)){
                attack.enemyNormalAttack();
            }
            else if (Math.abs(x - posx) > Math.abs(y - posy)) {

                if (x > posx) {
                    posx += step;
                    currentDirection = 'd';
                } else {
                    posx -= step;
                    currentDirection = 'a';
                }
            }
                else if (Math.abs(y - posy) > Math.abs(x - posx)) {
                    if (y > posy) {
                        posy += step;
                        currentDirection = 's';
                    } else {
                        posy -= step;
                        currentDirection = 'w';
                    }
                }
            }


        }

    }
