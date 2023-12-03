public class Weapon implements Collectable{
    private Player player;
    private String weaponName;
    private double weaponDamage;
    private int price;
    public Weapon(String weaponName, double weaponDamage, int price){
        this.weaponName = weaponName;
        this.weaponDamage = weaponDamage;
        this.price = price;
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

    @Override
    public void use(int e) {

    }

    @Override
    public int getPrice() {
        return this.price;
    }
}
