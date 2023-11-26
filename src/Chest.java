import java.util.ArrayList;

public class Chest {
    //Item class still needs to be instanitated
    private String name;
    private ArrayList<Weapon> weaponsList;
    private ArrayList<Item> itemList;

    public Chest(String name) {

        this.name = name;
    }

    public void chestRoll() {

        int range = (1 - 10) + 1;
        int decision = (int) (Math.random() * range) + 1;

        if (decision < 5) {

            this.giveItem();
        } else if (5 < decision) {
            this.giveWeapon();
        }
    }


    public Item giveItem() {
        return Item;
    }


    public Weapon giveWeapon() {
        return Weapon;
    }
}