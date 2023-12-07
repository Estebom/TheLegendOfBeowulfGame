public class ShortSword extends Weapon {

    private int multiplierCount = 0;
    //fast swing speed
    private int criticalHitKey = 7;

    private double weaponDamage = 0;

    public ShortSword(String weaponName, double weaponDamage, int price, Attack attack) {
        super(weaponName, weaponDamage, price, attack);

    }

    //use random number to be in charge of critical hit
    public void criticalHits(){

        int criticalAttempt = (int)(Math.random() * 10 - multiplierCount) - 1;
        if(criticalAttempt == criticalHitKey){
            this.weaponDamage = super.getWeaponDamage();
            super.setWeaponDamage(1.50);
            multiplierCount++;
            java.lang.System.out.println("i hit a crit");
        }



    } // can critical hit

    public void resetDamage(){
        super.setWeaponDamage(weaponDamage);
    }
    public void setMultiplierCount(int multiplierCount) {
        this.multiplierCount = multiplierCount;
    }
}
