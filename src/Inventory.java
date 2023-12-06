import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Inventory implements Serializable {
    // TODO: 12/2/2023  DEFINE AN INTERFACE FOR BOTH WEAPON AND ITEM
    private ArrayList<Item> items;
    private ArrayList<Weapon> weapons;

    private int currency;

    private Collectable collectableInUse = null;


    private Collectable[][] inventory = new Collectable[5][5];
    private static Inventory instance;

    private Inventory() {

        this.currency = 0;
    }

    private static Inventory getInstance() {
        if (instance == null) {
            instance = new Inventory();
        }
        return instance;
    }

    //for future make to map
    public static void addCollectable(Collectable collectable) {
        getInstance();
        for (int i = 0; i < 5; i++) {
            if (instance.inventory[i][0] == null) {
                instance.inventory[i][0] = collectable;
                return;
            } else {
                for (int j = 1; j < 5; j++) {
                    if (instance.inventory[i][j] == null) {
                        instance.inventory[i][j] = collectable;

                        return;
                    }
                }
            }
        }


    }

    public static void removeCollectable(Collectable collectable) {
        getInstance();
        for (int i = 0; i < 5; i++) {
            if (instance.inventory[i][0] == collectable) {
                instance.inventory[i][0] = null;
                return;
            } else {
                for (int j = 1; j < 5; j++) {
                    if (instance.inventory[i][j] == collectable) {
                        instance.inventory[i][j] = null;

                        return;
                    }
                }
            }
        }


    }

    public static void addCurrency(int currency) {
        getInstance();
        instance.currency += currency;

    }

    public static Collectable accessHotBar() {
        getInstance();

        return instance.collectableInUse;
    }

    public static void setInstance(Inventory loadedInstance) {
        getInstance();
        instance = loadedInstance;
    }

    public static void setCollectableInUse(int i) {
        getInstance();
        instance.collectableInUse = instance.inventory[0][i];
    }

    static public Collectable[][] getInventory() {
        getInstance();
        return instance.inventory;
    }

    static public int getCurrency() {
        getInstance();

        return instance.currency;
    }
    public static void writeToOutputStream(ObjectOutputStream outputStream)throws java.io.IOException{
        getInstance();
        outputStream.writeObject(instance);
    }
}
