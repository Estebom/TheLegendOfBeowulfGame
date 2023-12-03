import java.util.ArrayList;

public class Chest {
    //Item class still needs to be instanitated

    private String name;
    private ArrayList<Collectable> chestInventory; //first 5 slots will be empty
        // last 5 will be empty

    public Chest(String name) {

        this.name = name;
        chestInventory = new ArrayList<>();
    }

    public void chestRoll() {

        int range = (1 - 10) + 1;
        int decision = (int) (Math.random() * range) + 1;

        this.giveCollectable(decision);
    }

    public Collectable giveCollectable(int decision){
        Collectable collectable = chestInventory.get(decision);
        return collectable;
    }

}