import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private ArrayList<Item> items;
    private ArrayList<Weapon> weapons;

    private Object[][] inventory = new Object[5][5];
    private static Inventory instance;
    private Inventory(){}

    public static Inventory getInstance(){
        if(instance == null){
            instance = new Inventory();
        }
        return instance;
    }

    public void addWeapon(Weapon weapon){
        for(int i = 0; i < 5; i++){
            if(inventory[i][0] == null){
                inventory[i][0] = weapon;
                return;
            } else {
                for(int j = 1; j < 5; j++){
                    if(inventory[i][j] == null){
                        inventory[i][j] = weapon;
                        return;
                    }
                }
            }
        }

    }

    public void addItem(Item item){
        for(int i = 0; i < 5; i++){
            if(inventory[i][0] == null){
                inventory[i][0] = item;
                return;
            } else {
                for(int j = 1; j < 5; j++){
                    if(inventory[i][j] == null){
                        inventory[i][j] = item;
                        return;
                    }
                }
            }
        }
    }


}
