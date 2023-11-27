import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemInteraction implements ActionListener {
    private Interactions interactions;

    public ItemInteraction(Interactions interactions) {
        this.interactions = interactions;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Grabbing an item
        grabItem(new Item("Sample Item", "Sample Item Description"));
    }

    public void grabItem(Item item) {
        interactions.grabItem(item);
    }

    public void grabWeapon(Weapon weapon) {
        interactions.grabWeapon(weapon);
    }
}
