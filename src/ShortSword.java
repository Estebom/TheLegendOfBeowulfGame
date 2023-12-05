public class ShortSword extends Weapon {

    private int multiplierCount = 0;
    //fast swing speed
    private int criticalHitKey = 7;

    public ShortSword(String weaponName, double weaponDamage, int price, Attack attack) {
        super(weaponName, weaponDamage, price, attack);

    }

    //use random number to be in charge of critical hit
    public void criticalHits(){

        int criticalAttempt = (int)(Math.random() * 20 - multiplierCount) - 1;
        if(criticalAttempt == criticalHitKey){
            super.setWeaponDamage(1.70);
            multiplierCount++;
        }


    } // can critical hit

    public void setMultiplierCount(int multiplierCount) {
        this.multiplierCount = multiplierCount;
    }
}
