//Esteban Rodriguez & Alfonso Avila

import java.util.ArrayList;

public class NPC {

    private Inventory inventory;

    private String nameNPC;
    private ArrayList<String> dialogueList = new ArrayList<>();
    private ArrayList<Collectable> npcInventory = new ArrayList<>();
    private int dialougeSpot;

    private char direction = ' ';
    public NPC(String nameNPC, char direction){

        this.nameNPC = nameNPC;
        this.direction = direction;
        this.inventory = Inventory.getInstance();
        dialougeSpot = 0;
    }


    public void addNPCInventory(Collectable collectable){

        npcInventory.add(collectable);

    }

    public Collectable buy(int selectedCollectable){

        Collectable collectable = npcInventory.get(selectedCollectable);
        npcInventory.remove(selectedCollectable);
        return collectable;

    }

    public void sell(Collectable collectable){

            inventory.addCurrency(collectable.getPrice());
            inventory.removeCollectable(collectable);
    }

    public String speak(){


        String dialougeLine = dialogueList.get(dialougeSpot);
        this.dialougeSpot += 1;

        return dialougeLine;
    }


}
