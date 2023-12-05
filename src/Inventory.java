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
    private Inventory(){

        this.currency = 0;
    }

    public static Inventory getInstance(){
        if(instance == null){
            instance = new Inventory();
        }
        return instance;
    }

    //for future make to map
    public void addCollectable(Collectable collectable){
        for(int i = 0; i < 5; i++){
            if(inventory[i][0] == null){
                inventory[i][0] = collectable;
                return;
            } else {
                for(int j = 1; j < 5; j++){
                    if(inventory[i][j] == null){
                        inventory[i][j] = collectable;

                        return;
                    }
                }
            }
        }


    }

    public void removeCollectable(Collectable collectable){

        for(int i = 0; i < 5; i++){
            if(inventory[i][0] == collectable){
                inventory[i][0] = null;
                return;
            } else {
                for(int j = 1; j < 5; j++){
                    if(inventory[i][j] == collectable){
                        inventory[i][j] = null;

                        return;
                    }
                }
            }
        }



    }

    public void addCurrency(int currency){

        this.currency += currency;

    }

    public Collectable accessHotBar(){
        return this.collectableInUse;
    }

    public void setCollectableInUse(int i){
        this.collectableInUse = inventory[0][i];
    }

}
