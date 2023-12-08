import java.io.Serializable;
import java.util.ArrayList;

/**
 * chest class is responsible for giving out items to players
 * @author Esteban Rodriguez
 */
public class Chest extends ChestImages implements Serializable {


    private String name;

    private int chestPosX;
    private int chestPosY;
    private char direction = ' ';

    private ArrayList<Collectable> chestInventory;


    public Chest(String name, char direction) {

        this.name = name;
        this.direction = direction;
        chestInventory = new ArrayList<>();
    }

    public Collectable openChest() {

        int range = (1 - 5) + 1;
        int decision = (int) (Math.random() * range) + 1;

        Collectable collectable = chestInventory.get(decision);
        return collectable;

    }

    public void addCollectable(Collectable collectable){
        chestInventory.add(collectable);


    }
    public int getChestPosX(){
        return this.chestPosX;

    }
    public int getChestPosY(){
        return this.chestPosY;
    }
    public char getDirection(){
        return this.direction;
    }



}