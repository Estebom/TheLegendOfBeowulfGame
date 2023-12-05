import javax.swing.*;

public class System {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainDisplay());
        Enemy enemy = new Enemy("Goblin");
        Attack attack = new Attack(enemy);

        ShortSword sword = new ShortSword("speedy", 200.0, 200, attack);
        Inventory.getInstance().addCollectable(sword);












    }


}
