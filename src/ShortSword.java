public class ShortSword extends Weapon {
    private int swingSpeed; //fast
    public ShortSword(String weaponName, double weaponDamage, int price) {
        super(weaponName, weaponDamage, price);
    }

    public void criticalHits(){} // can critical hit
}
