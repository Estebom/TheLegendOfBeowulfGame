import java.io.Serializable;

/**
 * Esteban Rodriguez
 */
public class Weapon implements Collectable, Serializable {
    private String weaponName;
    private double weaponDamage;
    //private int swingSpeed = 50;
    private int price;
    private Attack attack;



    private long t1,t2 = 0;


    public Weapon(String weaponName, double weaponDamage, int price, Attack attack){
        this.weaponName = weaponName;
        this.weaponDamage = weaponDamage;
        this.price = price;
        this.attack = attack;
    }

    public Weapon(Collectable collectable, GamePlay gamePlay) {
        if (collectable instanceof Weapon) {
            Weapon weapon = (Weapon) collectable;
            this.weaponName = weapon.getWeaponName();
            this.weaponDamage = weapon.getWeaponDamage();
            this.price = weapon.getPrice();
            this.attack = new Attack(GamePlay.getCurrentTarget());
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
            Player.setDamageOutput(Player.getDamageOutput() + weaponDamage);

        }
        else {
            Player.setDamageOutput(Player.getDamageOutput() - weaponDamage);
        }

    }

    @Override
    public void use(boolean b, int t) {
        java.lang.System.out.println("using");


            if(t > 1500){
                //special attack
                attack.playerSpecialAttack();
                java.lang.System.out.println("special attack");
            }
            else {

                attack.playerNormalAttack();
                java.lang.System.out.println("Normal attack");
                //normal attack
            }
        }



    public void swing(boolean b, int t){

        if(b == false){
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

    @Override
    public String getName() {
        return this.weaponName;
    }
}
