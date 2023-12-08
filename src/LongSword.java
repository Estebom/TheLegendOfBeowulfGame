public class LongSword extends Weapon {
    private int swingSpeed; //slow
    public LongSword(String weaponName, double weaponDamage, int price, Attack attack) {
        super(weaponName, weaponDamage, price, attack);
    }
    public void pushBack(){
        //trait : pushes ememies back
    }
}
