public class MidSword extends Weapon {
    private int swingSpeed; //avg
    public MidSword(String weaponName, double weaponDamage, int price, Attack attack) {
        super(weaponName, weaponDamage, price, attack);
    }

    public void holdOff(){} //keeps enemy at arms reach
 }
