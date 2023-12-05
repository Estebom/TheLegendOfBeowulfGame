import java.io.Serializable;

public class Weapon implements Collectable, Serializable {
    private Player player;
    private String weaponName;
    private double weaponDamage;
    private int price;
    private Attack attack;
    private int t1,t2;


    public Weapon(String weaponName, double weaponDamage, int price){
        this.weaponName = weaponName;
        this.weaponDamage = weaponDamage;
        this.price = price;
        player = Player.getInstance();
    }

    public Weapon(Collectable collectable) {
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
    public void use(){

    }

    @Override
    public int getPrice() {
        return this.price;
    }
}
