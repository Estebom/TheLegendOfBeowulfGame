import javax.swing.*;

public class System {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> MainDisplay.getInstance());
        Enemy enemy = new Enemy("Goblin");
        Attack attack = new Attack(enemy);

        ShortSword sword = new ShortSword("speedy", 1000.0, 200, attack);
        Inventory.addCollectable(sword);


    }


}
