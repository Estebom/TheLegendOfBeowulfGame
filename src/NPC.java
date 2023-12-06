//Esteban Rodriguez & Alfonso Avila

import java.util.ArrayList;

public class NPC {



    private String nameNPC;
    private ArrayList<String> dialogueList = new ArrayList<>();
    private ArrayList<Collectable> npcInventory = new ArrayList<>();
    private int dialougeSpot;

    private char direction = ' ';
    public NPC(String nameNPC, char direction){

        this.nameNPC = nameNPC;
        this.direction = direction;

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

            Inventory.addCurrency(collectable.getPrice());
            Inventory.removeCollectable(collectable);
    }

    public String speak(){


        String dialougeLine = dialogueList.get(dialougeSpot);
        this.dialougeSpot += 1;

        return dialougeLine;
    }


}
