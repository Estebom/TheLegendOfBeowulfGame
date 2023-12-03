
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interaction extends JPanel {
    private Inventory inventory;

    private NPC npc;
    private Chest chest;
    private JButton buyButton;
    private JButton sellButton;
    private JButton speakButton;

    public Interaction(NPC npc) {

        this.inventory = Inventory.getInstance();
        this.npc = npc;

        buyButton = new JButton("Buy from NPC");
        sellButton = new JButton("Sell to NPC");
        speakButton = new JButton("Speak to NPC");



        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Provide a way to select the collectable to buy
                int selectedCollectable = 0;/* implement your logic to get the selected collectable */;
                npc.buy(selectedCollectable);
            }
        });

        sellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Provide a way to select the collectable to sell
                ShortSword sword = new ShortSword("sword", 2.3, 2);/* implement your logic to get the selected collectable */;
                npc.sell(sword);
            }
        });

        speakButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dialogue = npc.speak();
                // Display the dialogue in your UI
                @// TODO: 12/3/2023 write the actionListener functions out of the constructor and have the listener get the message then delegate instructions 
                @// TODO: 12/3/2023 instantiate a panel
                JOptionPane.showMessageDialog(.this, dialogue, "NPC Dialogue", JOptionPane.INFORMATION_MESSAGE);
            }
        });


        add(buyButton);
        add(sellButton);
        add(speakButton);

    }
    public void grabCollectable(Collectable collectable) {
        inventory.addCollectable(collectable);
    }

    public void openChest(){
        inventory.addCollectable(chest.chestRoll());
    }
}

