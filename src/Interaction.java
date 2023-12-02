import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.awt.event.ActionListener;

public class Interaction implements ActionListener {
    private Inventory inventory;

    public Interaction() {
        this.inventory = Inventory.getInstance();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Sample logic for grabbing an item or weapon from a chest
        Chest chest = new Chest("Sample Chest");
        Item item = chest.giveItem();
        Weapon weapon = chest.giveWeapon();

        // Add the grabbed item and weapon to the player's inventory
        grabItem(item);
        grabWeapon(weapon);

        // Optionally, you can perform additional actions based on the specific item or weapon
        if (weapon instanceof LongSword) {
            ((LongSword) weapon).pushBack();
        } else if (weapon instanceof MidSword) {
            ((MidSword) weapon).holdOff();
        } else if (weapon instanceof ShortSword) {
            ((ShortSword) weapon).criticalHits();
        }
    }

    public void grabItem(Item item) {
        inventory.addItem(item);
    }

    public void grabWeapon(Weapon weapon) {
        inventory.addWeapon(weapon);
    }
}
