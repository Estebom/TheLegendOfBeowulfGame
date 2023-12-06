//Esteban Rodriguez & Alfonso Avila
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Used to handle keyboard inputs during GamePlay
 * @author Esteban
 */
public class KeyPad extends KeyAdapter {

    private Interaction interaction;
    private Attack attack;
    private NPC npc;

    private static KeyPad instance;

    private boolean readKeys = true;

    private KeyPad() {
        this.interaction = new Interaction();
        this.attack = new Attack(GamePlay.getCurrentTarget());
        this.npc = new NPC("Sample NPC", 'N'); // Replace with actual NPC initialization
    }

    public static void setupEscapeKeyBinding(JComponent component, Runnable actionToPerform) {
        if (component == null) {
            throw new IllegalArgumentException("Component cannot be null");
        }

        KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        InputMap inputMap = component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = component.getActionMap();




        inputMap.put(escapeKeyStroke, "performAction");
        actionMap.put("performAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionToPerform.run();
            }
        });
    }

    public static void setupMKeyBinding(JComponent component, Runnable actionToPerform) {
        if (component == null) {
            throw new IllegalArgumentException("Component cannot be null");
        }

        KeyStroke mkeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_M,0);
        InputMap inputMap = component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = component.getActionMap();

        inputMap.put(mkeyStroke, "openInventory");
        actionMap.put("openInventory", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                actionToPerform.run();
            }
        });
    }

    public static KeyPad getInstance(GamePlay gamePlay) {
        if (instance == null) {
            instance = new KeyPad();
        }
        return instance;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (readKeys) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_1:
                case KeyEvent.VK_2:
                case KeyEvent.VK_3:
                case KeyEvent.VK_4:
                case KeyEvent.VK_5:
                    int digit = e.getKeyChar() - '0';
                    Inventory.setCollectableInUse(digit - 1);
                    Collectable collectable = Inventory.accessHotBar();
                    if (collectable != null && collectable.getClass() == Weapon.class) {
                        Weapon weapon = (Weapon) collectable;
                        weapon.modifyPlayerDamage(true);
                    }
                    break;
                case KeyEvent.VK_W:
                    java.lang.System.out.println("w");
                case KeyEvent.VK_A:
                    java.lang.System.out.println("a");
                case KeyEvent.VK_S:
                    java.lang.System.out.println("s");
                case KeyEvent.VK_D:
                    java.lang.System.out.println("d");
                    Player.move(e.getKeyChar());
                    break;
                case KeyEvent.VK_E:
                    java.lang.System.out.println("use collectable");
                    int t1 = (int) java.lang.System.currentTimeMillis();
                    Collectable hotbarCollectable = Inventory.accessHotBar();
                    if (hotbarCollectable != null) {
                        hotbarCollectable.use(false, t1);
                    }
                    break;
                case KeyEvent.VK_Q:
                    java.lang.System.out.println("Interact");
                    //handleInteraction();
                    break;
                case KeyEvent.VK_O:
                    java.lang.System.out.println("attack");
                    break;
                case KeyEvent.VK_P:
                    java.lang.System.out.println("special attack");
                    break;
                case KeyEvent.VK_M:
                    java.lang.System.out.println("inventory open");
                    Inventory.inventoryToggle();
                    // toggle to close
                    break;
                case KeyEvent.VK_N:
                    java.lang.System.out.println("dropped item");
                    break;
                case KeyEvent.VK_ESCAPE:
                    java.lang.System.out.println("escape pressed");
                    GamePlay.showGameMenu();
                    this.readKeys = false;
                    // Add your action for ESC key here
                    break;
            }
        }
    }

//    private void handleInteraction() {
//        if (isNPCInRange()) {
//            npcInteraction();
//        }
//    }

//    private boolean isNPCInRange() {
//        int playerX = Player.getPlayerPosX(); // Replace with actual player position retrieval
//        int playerY = Player.getPlayerPosY();
//        int npcX = npc.getX();
//        int npcY = npc.getY();
//
//        double distance = Math.sqrt(Math.pow(playerX - npcX, 2) + Math.pow(playerY - npcY, 2));
//        double interactionThreshold = 50.0;
//
//        return distance <= interactionThreshold;
//    }

//    private void npcInteraction() {
//        java.lang.System.out.println("Interacting with NPC: " + npc.getNameNPC());
//        // Add any additional logic for the NPC interaction
//    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_A:
            case KeyEvent.VK_S:
            case KeyEvent.VK_D:
                Player.stopMoving();
                break;
            case KeyEvent.VK_E:
                int t2 = (int) java.lang.System.currentTimeMillis();
                Collectable collectable = Inventory.accessHotBar();

                if (collectable != null && collectable instanceof Weapon) {
                    Weapon weapon = (Weapon) collectable;
                    if (GamePlay.hittable()) {
                        weapon.use(true, t2);

                        if (weapon instanceof ShortSword) {
                            ShortSword shortSword = (ShortSword) weapon;
                            shortSword.criticalHits();
                        }
                    } else {
                        weapon.swing(true, t2);
                        if (weapon instanceof ShortSword) {
                            ShortSword shortSword = (ShortSword) weapon;
                            shortSword.setMultiplierCount(0);
                            java.lang.System.out.println("i swung");
                        }
                    }
                } else if (collectable instanceof Item) {
                    Item item = (Item) collectable;
                    item.use(true, t2);
                }
                break;
        }
    }

    public static void setReadable(boolean readable) {

        instance.readKeys = readable;
    }
}








/**
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;


 * used to handle keyboard inputs during GamePlay
 * @author Esteban

public class KeyPad extends KeyAdapter {

    private Interaction interaction;
    private Attack attack;




    private static KeyPad instance;


    private boolean readKeys = true;

    private KeyPad() {

        this.interaction = new Interaction();
        this.attack = new Attack(GamePlay.getCurrentTarget());


    }

    public static void setupEscapeKeyBinding(JComponent component, Runnable actionToPerform) {
        if (component == null) {
            throw new IllegalArgumentException("Component cannot be null");
        }

        KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        InputMap inputMap = component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = component.getActionMap();

        inputMap.put(escapeKeyStroke, "performAction");
        actionMap.put("performAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionToPerform.run();
            }
        });
    }


    public static KeyPad getInstance(GamePlay gamePlay) {

        if (instance == null) {
            instance = new KeyPad();
        }
        return instance;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (readKeys == true) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_1:
                case KeyEvent.VK_2:
                case KeyEvent.VK_3:
                case KeyEvent.VK_4:
                case KeyEvent.VK_5:
                    int digit = e.getKeyChar() - '0';
                    Inventory.setCollectableInUse(digit - 1);
                    Collectable collectable = Inventory.accessHotBar();
                    if (collectable != null && collectable.getClass() == Weapon.class) {
                        Weapon weapon = (Weapon) collectable;
                        weapon.modifyPlayerDamage(true);
                    }
                    break;
                case KeyEvent.VK_W:
                    java.lang.System.out.println("w");
                case KeyEvent.VK_A:
                    java.lang.System.out.println("a");
                case KeyEvent.VK_S:
                    java.lang.System.out.println("s");
                case KeyEvent.VK_D:
                    java.lang.System.out.println("d");
                    Player.move(e.getKeyChar());
                    break;
                case KeyEvent.VK_E:
                    java.lang.System.out.println("use collectable");
                    int t1 = (int) java.lang.System.currentTimeMillis();
                    Collectable hotbarCollectable = Inventory.accessHotBar();
                    if (hotbarCollectable != null) {
                        hotbarCollectable.use(false, t1);
                    }
                    break;
                case KeyEvent.VK_Q:
                    java.lang.System.out.println("interact");

                    break;
                case KeyEvent.VK_O:
                    java.lang.System.out.println("attack");
                    break;
                case KeyEvent.VK_P:
                    java.lang.System.out.println("special attack");
                    break;
                case KeyEvent.VK_M:
                    java.lang.System.out.println("inventory open");
                    //toggle to close
                    break;
                case KeyEvent.VK_N:
                    java.lang.System.out.println("dropped item");
                    break;
                case KeyEvent.VK_ESCAPE:
                    java.lang.System.out.println("escape pressed");

                    GamePlay.showGameMenu();

                    this.readKeys = false;
                    // Add your action for ESC key here
                    break;

            }

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_A:
            case KeyEvent.VK_S:
            case KeyEvent.VK_D:
                Player.stopMoving();
                break;
            case KeyEvent.VK_E:
                int t2 = (int) java.lang.System.currentTimeMillis();
                Collectable collectable = Inventory.accessHotBar();

                if (collectable != null && collectable instanceof Weapon) {
                    Weapon weapon = (Weapon) collectable;
                    if (GamePlay.hittable()) {
                        weapon.use(true, t2);

                        if (weapon instanceof ShortSword) {
                            ShortSword shortSword = (ShortSword) weapon;
                            shortSword.criticalHits();
                        }
                    } else {
                        weapon.swing(true, t2);
                        if (weapon instanceof ShortSword) {
                            ShortSword shortSword = (ShortSword) weapon;
                            shortSword.setMultiplierCount(0);
                            java.lang.System.out.println("i swung");
                        }
                    }
                } else if (collectable instanceof Item) {
                    Item item = (Item) collectable;
                    item.use(true, t2);
                }
                break;
        }



    }
    public static void setReadable(boolean readable){
        instance.readKeys = readable;
    }
}
 */