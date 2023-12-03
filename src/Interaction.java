import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InteractionPanel extends JPanel {
    private Interaction interaction;
    private JButton openChestButton;
    private JButton buyButton;
    private JButton sellButton;
    private JButton speakButton;

    public InteractionPanel(Interaction interaction) {
        this.interaction = interaction;

        openChestButton = new JButton("Open Chest");
        buyButton = new JButton("Buy from NPC");
        sellButton = new JButton("Sell to NPC");
        speakButton = new JButton("Speak to NPC");

        openChestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                interaction.openChest();
            }
        });

        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Provide a way to select the collectable to buy
                int selectedCollectable = /* implement your logic to get the selected collectable */;
                interaction.buyFromNPC(selectedCollectable);
            }
        });

        sellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Provide a way to select the collectable to sell
                Collectable selectedCollectable = /* implement your logic to get the selected collectable */;
                interaction.sellToNPC(selectedCollectable);
            }
        });

        speakButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dialogue = interaction.speakToNPC();
                // Display the dialogue in your UI
                JOptionPane.showMessageDialog(InteractionPanel.this, dialogue, "NPC Dialogue", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        add(openChestButton);
        add(buyButton);
        add(sellButton);
        add(speakButton);
    }
}