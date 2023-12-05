import java.util.ArrayList;

public class Chest {


    private String name;
    private char direction = ' ';

    private ArrayList<Collectable> chestInventory; //first 5 slots will be empty
        // last 5 will be empty

    public Chest(String name, char direction) {

        this.name = name;
        this.direction = direction;
        chestInventory = new ArrayList<>();
    }

    public Collectable chestRoll() {

        int range = (1 - 10) + 1;
        int decision = (int) (Math.random() * range) + 1;

        Collectable collectable = chestInventory.get(decision);
        return collectable;

    }



}