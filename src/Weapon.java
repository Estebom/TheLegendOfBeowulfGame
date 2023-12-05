import java.io.Serializable;

/**
 * Esteban Rodriguez
 */
public class Weapon implements Collectable, Serializable {
    private Player player;
    private String weaponName;
    private double weaponDamage;
    //private int swingSpeed = 50;
    private int price;
    private Attack attack;



    private int t1,t2;


    public Weapon(String weaponName, double weaponDamage, int price, Attack attack){
        this.weaponName = weaponName;
        this.weaponDamage = weaponDamage;
        this.price = price;
        player = Player.getInstance();
        this.attack = attack;

    }

    public Weapon(Collectable collectable, GamePlay gamePlay) {
        if (collectable instanceof Weapon) {
            Weapon weapon = (Weapon) collectable;
            this.weaponName = weapon.getWeaponName();
            this.weaponDamage = weapon.getWeaponDamage();
            this.price = weapon.getPrice();
            this.player = Player.getInstance();
            this.attack = new Attack(gamePlay);
        }
    }



    public String getWeaponName() {
        return weaponName;
    }
    public double getWeaponDamage(){
        return weaponDamage;
    }


    public void modifyPlayerDamage(boolean b){

        if (b == true){
           player.setDamageOutput(player.getDamageOutput() + weaponDamage);

        }
        else {
            player.setDamageOutput(player.getDamageOutput() - weaponDamage);
        }

    }

    @Override
    public void use(boolean b, int t) {

        if(b = false){
            this.t1 = t;
        }
        else {
            this.t2 =t;
            if(this.t2 - this.t1 > 1500){
                //special attack
                attack.playerSpecialAttack();
            }
            else {

                attack.playerNormalAttack();
                //normal attack
            }
        }


    }
    public void swing(boolean b, int t){

        if(b = false){
            this.t1 = t;
        }
        else {
            this.t2 =t;
            if(this.t2 - this.t1 > 1500){
                //special attack
                // long swing animation no hit
            }
            else {

                //swing animation
            }
        }

    }
    public void setWeaponDamage(double multiplier){
        this.weaponDamage *= multiplier;
    }

    @Override
    public int getPrice() {
        return this.price;
    }
}
