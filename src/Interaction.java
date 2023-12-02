import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interaction {
    private Inventory inventory;
    private Chest chest;
    public Interaction() {

        this.inventory = Inventory.getInstance();

    }



    public void grabCollectable(Collectable collectable) {
        inventory.addCollectable(collectable);
    }

    public void openChest(){
        chest.chestRoll();
    }





}