import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interaction extends JPanel {

    private NPC npc;
    private Chest chest;
    private JButton buyButton;
    private JButton sellButton;
    private JButton speakButton;

    public Interaction() {
    }

    public Interaction(NPC npc) {

        this.npc = npc;

        buyButton = new JButton("Buy from NPC");
        sellButton = new JButton("Sell to NPC");
        speakButton = new JButton("Speak to NPC");

        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showBuyPanel(); // Delegate instructions to a separate method
            }
        });

        sellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSellPanel(); // Delegate instructions to a separate method
            }
        });

        speakButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSpeakPanel(); // Delegate instructions to a separate method
            }
        });

        add(buyButton);
        add(sellButton);
        add(speakButton);
    }

    public void grabCollectable(Collectable collectable) {
        Inventory.addCollectable(collectable);
    }

    public void openChest() {
        Inventory.addCollectable(chest.chestRoll());
    }

    // TODO: 12/3/2023 write the actionListener functions out of the constructor and have the listener get the message then delegate instructions
    private void showBuyPanel() {
        // Instantiate a new panel for buying options
        JFrame buyFrame = new JFrame("Buy from NPC");
        JPanel buyPanel = new JPanel();
        // Add components to the buyPanel as needed

        // Example: Display a message in a JOptionPane
        String message = "Choose an item to buy.";
        JOptionPane.showMessageDialog(this, message, "Buy from NPC", JOptionPane.INFORMATION_MESSAGE);

        buyFrame.add(buyPanel);
        buyFrame.setSize(300, 200);
        buyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        buyFrame.setVisible(true);
    }

    // TODO: 12/3/2023 write the actionListener functions out of the constructor and have the listener get the message then delegate instructions
    private void showSellPanel() {
        // Instantiate a new panel for selling options
        JFrame sellFrame = new JFrame("Sell to NPC");
        JPanel sellPanel = new JPanel();
        // Add components to the sellPanel as needed

        // Example: Display a message in a JOptionPane
        String message = "Choose an item to sell.";
        JOptionPane.showMessageDialog(this, message, "Sell to NPC", JOptionPane.INFORMATION_MESSAGE);

        sellFrame.add(sellPanel);
        sellFrame.setSize(300, 200);
        sellFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        sellFrame.setVisible(true);
    }

    // TODO: 12/3/2023 write the actionListener functions out of the constructor and have the listener get the message then delegate instructions
    private void showSpeakPanel() {
        // Instantiate a new panel for speaking options
        JFrame speakFrame = new JFrame("Speak to NPC");
        JPanel speakPanel = new JPanel();
        // Add components to the speakPanel as needed

        // Example: Display a message in a JOptionPane
        String dialogue = npc.speak();
        JOptionPane.showMessageDialog(this, dialogue, "NPC Dialogue", JOptionPane.INFORMATION_MESSAGE);

        speakFrame.add(speakPanel);
        speakFrame.setSize(300, 200);
        speakFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        speakFrame.setVisible(true);
    }
}
