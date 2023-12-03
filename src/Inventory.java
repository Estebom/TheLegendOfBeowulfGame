import java.util.ArrayList;
import java.util.List;

public class Inventory {
    // TODO: 12/2/2023  DEFINE AN INTERFACE FOR BOTH WEAPON AND ITEM
    private ArrayList<Item> items;
    private ArrayList<Weapon> weapons;

    private int currency;


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



}
