public class MidSword extends Weapon{
    private int swingSpeed; //avg
    public MidSword(String weaponName, double weaponDamage, int price) {
        super(weaponName, weaponDamage, price);
    }

    public void holdOff(){} //keeps enemy at arms reach
 }
