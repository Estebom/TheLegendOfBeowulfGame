import java.util.ArrayList;

public class Chest {
    //Item class still needs to be instanitated
    private String name;
    private ArrayList<Weapon> weaponsList; //first 5 slots will be empty
    private ArrayList<Item> itemList;   // last 5 will be empty

    public Chest(String name) {

        this.name = name;
    }

    public void chestRoll() {

        int range = (1 - 10) + 1;
        int decision = (int) (Math.random() * range) + 1;

        if (decision < 5) {

            this.giveItem(decision);
        } else if (5 < decision) {
            this.giveWeapon(decision);
        }
    }


    public Item giveItem(int decision) {
        return itemList.get(decision);
    }


    public Weapon giveWeapon(int decision) {

        return weaponsList.get(decision);
    }
}