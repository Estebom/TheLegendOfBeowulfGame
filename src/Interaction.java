////Esteban Rodriguez & Alfonso Avila

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



    public void grabCollectable(Collectable collectable) {
        Inventory.addCollectable(collectable);
    }

    public void openChest() {
        Inventory.addCollectable(chest.openChest());
        java.lang.System.out.println("opening chest");

    }


  public void setChest(Chest chest){
        this.chest = chest;
      java.lang.System.out.println("you are near a chest");
  }


    public void npcInteraction(NPC npc){

        this.npc = npc;
        this.npc.showInteractionPanel();

    }

    // TODO: 12/3/2023 write the actionListener functions out of the constructor and have the listener get the message then delegate instructions


    // TODO: 12/3/2023 write the actionListener functions out of the constructor and have the listener get the message then delegate instructions


    // TODO: 12/3/2023 write the actionListener functions out of the constructor and have the listener get the message then delegate instructions

}
