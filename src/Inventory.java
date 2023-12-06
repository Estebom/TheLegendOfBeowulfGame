import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Inventory extends JPanel implements Serializable {
    // TODO: 12/2/2023  DEFINE AN INTERFACE FOR BOTH WEAPON AND ITEM
    private JButton toggleButton;
    private JPanel inventoryPanel;
    private ArrayList<Item> items;
    private ArrayList<Weapon> weapons;
    private  boolean inventoryVisible = false;

    private int currency = 0;

    private Collectable collectableInUse = null;


    private Collectable[][] inventory = new Collectable[5][5];
    private static Inventory instance;

    public Inventory() {

        this.setSize(new Dimension(100,100));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.BLUE);
        this.setOpaque(true);


        inventoryPanel = new JPanel();
        inventoryPanel.setLayout(new GridLayout(4, 3));


        for (int i = 0; i < 12; i++) {
            JPanel slot = createInventorySlot();
            add(slot);
        }

        this.add(inventoryPanel);
        this.setVisible(false);


    }
    private JPanel createInventorySlot() {
        JPanel slot = new JPanel();
        slot.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        slot.setPreferredSize(new Dimension(50, 50));

        // Add mouse listener to handle interactions
        slot.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                java.lang.System.out.println("Slot clicked");
                // Implement logic to interact with inventory items or weapons here
            }
        });

        return slot;
    }

    public static Inventory getInstance() {
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

    public static void addWeapon(Weapon weapon) {
        getInstance();
        if (instance.weapons == null) {
            instance.weapons = new ArrayList<>();
        }
        instance.weapons.add(weapon);
    }

    public static void removeWeapon(Weapon weapon) {
        getInstance();
        if (instance.weapons != null) {
            instance.weapons.remove(weapon);
        }
    }

    public static ArrayList<Weapon> getWeapons() {
        getInstance();
        return instance.weapons;
    }





//    public void inventoryUI() {
//        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//
//        toggleButton = new JButton("Toggle Inventory");
//        toggleButton.addActionListener(new InventoryToggleListener());
//        add(toggleButton);
//
//        inventoryPanel = new JPanel();
//        inventoryPanel.setLayout(new GridLayout(4, 3));
//        inventoryPanel.setVisible(false);
//
//        for (int i = 0; i < 12; i++) {
//            JPanel slot = createInventorySlot();
//            add(slot);
//        }
//
//        add(inventoryPanel);
//    }



    public static void inventoryToggle(){
        getInstance();

                instance.inventoryVisible = !instance.inventoryVisible;
                instance.setVisible(instance.inventoryVisible);


    }

//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Game Window");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(400, 300);
//
//        Inventory inventory = new Inventory();
//        frame.add(inventory);
//
//        frame.setVisible(true);
//    }
}
