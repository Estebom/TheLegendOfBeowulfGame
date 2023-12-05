import java.io.Serializable;

public class Weapon implements Collectable, Serializable {
    private String weaponName;
    private double weaponDamage;
    private int price;
    public Weapon(String weaponName, double weaponDamage, int price){
        this.weaponName = weaponName;
        this.weaponDamage = weaponDamage;
        this.price = price;
    }

    public String getWeaponName() {
        return weaponName;
    }
    public double getWeaponDamage(){
        return weaponDamage;
    }


    public void modifyPlayerDamage(boolean b){

        if (b == true){
            PlayerSprite.setDamageOutput(PlayerSprite.getDamageOutput() + weaponDamage);

        }
        else {
            PlayerSprite.setDamageOutput(PlayerSprite.getDamageOutput() - weaponDamage);
        }

    }

    @Override
    public void use(int e) {

    }

    @Override
    public int getPrice() {
        return this.price;
    }
}
