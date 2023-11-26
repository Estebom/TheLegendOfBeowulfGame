public class Weapon {
    private Player player;
    private String weaponName;
    private double weaponDamage;
    public Weapon(String weaponName, double weaponDamage){
        this.weaponName = weaponName;
        this.weaponDamage = weaponDamage;
        player = Player.getInstance();
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

}
