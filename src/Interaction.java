import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interaction implements ActionListener {
    private Inventory inventory;

    public Interaction() {

        this.inventory = Inventory.getInstance();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Grabbing an item
        grabItem(new Item("Sample Item", "Sample Item Description"));
    }

    public void grabItem(Item item) {
        inventory.addItem(item);
    }

    public void grabWeapon(Weapon weapon) {
        inventory.addWeapon(weapon);
    }
}