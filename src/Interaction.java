// Importing necessary packages
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is the Interaction class with the NPC's
 * @authors Esteban Rodriguez & Alfonso Avila
 */

// Class representing the interaction panel
public class Interaction extends JPanel {

    // Declaration of instance variables
    private NPC npc;
    private Chest chest;
    private JButton buyButton;
    private JButton sellButton;
    private JButton speakButton;

    // Default constructor
    public Interaction() {

    }

    // Method to add a collectable item to the player's inventory
    public void grabCollectable(Collectable collectable) {
        Inventory.addCollectable(collectable);
    }

    // Method to open a chest and add a collectable item to the player's inventory
    public void openChest() {
        Inventory.addCollectable(chest.openChest());
        java.lang.System.out.println("opening chest");

    }



  public void setChest(Chest chest){
        this.chest = chest;
      java.lang.System.out.println("you are near a chest");
  }



    public void npcInteraction(NPC npc){
        // Set the current NPC and display the interaction panel
        this.npc = npc;
        this.npc.showInteractionPanel();
    }

    // TODO: 12/3/2023 write the actionListener functions out of the constructor and have the listener get the message then delegate instructions

    // TODO: 12/3/2023 write the actionListener functions out of the constructor and have the listener get the message then delegate instructions

    // TODO: 12/3/2023 write the actionListener functions out of the constructor and have the listener get the message then delegate instructions
}
