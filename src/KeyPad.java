//Esteban Rodriguez & Alfonso Avila
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Used to handle keyboard inputs during GamePlay
 * @author Esteban
 */
public class KeyPad extends KeyAdapter {

    private Interaction interaction;



    private static KeyPad instance;
    private long pressTime = 0;


    private boolean readKeys = true;
    private int inventoryToggle = 1;

    private KeyPad() {
        this.interaction = new Interaction();

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

    public static void setupQKeyBinding(JComponent component, Runnable actionToPerform) {
        if (component == null) {
            throw new IllegalArgumentException("Component cannot be null");
        }

        KeyStroke mkeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_Q,0);
        InputMap inputMap = component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = component.getActionMap();

        inputMap.put(mkeyStroke, "openInteraction");
        actionMap.put("openInteraction", new AbstractAction() {
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
                case KeyEvent.VK_A:
                case KeyEvent.VK_S:
                case KeyEvent.VK_D:
                    Player.move(e.getKeyChar());
                    break;
                case KeyEvent.VK_E:
//                    java.lang.System.out.println("use collectable");
//                    int t1 = (int) java.lang.System.currentTimeMillis();
//                    Collectable hotbarCollectable = Inventory.accessHotBar();
//                    if (hotbarCollectable != null) {
//                        hotbarCollectable.use(false, t1);
//                    }
                    if(Inventory.accessHotBar() instanceof Weapon){
                        Player.startAttack();
                        if (pressTime == 0) { // Only set if not already pressed
                        pressTime = java.lang.System.currentTimeMillis();
                        }
                    }
                    break;

                case KeyEvent.VK_Q:
                    java.lang.System.out.println("Interact");
                    interaction.handleChestInteraction();
                    handleInteraction();
                    break;

                case KeyEvent.VK_M:
                    java.lang.System.out.println("inventory open");
                    Inventory.inventoryToggle();
                    if(inventoryToggle == 1) {
                        GamePlay.pauseGame();
                        inventoryToggle++;
                    }
                    else {
                        GamePlay.startGame();
                        inventoryToggle = 1;
                    }
                    break;
                case KeyEvent.VK_N:
                    java.lang.System.out.println("dropped item");
                    break;
                case KeyEvent.VK_ESCAPE:
                    java.lang.System.out.println("escape pressed");
                    GamePlay.showGameMenu();
                    GamePlay.pauseGame();

                    this.readKeys = false;
                    // Add your action for ESC key here
                    break;
            }
        }
    }

    private void handleInteraction() {
        if (isNPCInRange()) {
            java.lang.System.out.println("interacting");
            GamePlay.showInteraction();

          // interaction.npcInteraction(GamePlay.accessNPC());
        }
    }




    public static Chest isChestInRange() {

        int playerX = Player.getPlayerPosX();
        int playerY = Player.getPlayerPosY();




        char playerDirection = Player.getCurrentDirection();

        ArrayList<Chest> chests = GamePlay.accessChests();

        double distance = 0;
        double interactionThreshold = 50.0;

        for(Chest chest : chests){
            char chestDirection = chest.getDirection();
            int chestX = chest.getChestPosX();
            int chestY = chest.getChestPosY();
            distance = Math.max(distance, Math.sqrt(Math.pow(playerX - chestX, 2) + Math.pow(playerY - chestY, 2)));

            boolean b = (distance <= interactionThreshold) &&
                    (playerDirection == 'w' && chestDirection == 's' ||
                            playerDirection == 's' && chestDirection == 'w' ||
                            playerDirection == 'a' && chestDirection == 'd' ||
                            playerDirection == 'd' && chestDirection == 'a');

            if (b){return chest;}

        }
            return null;

    }


    private boolean isNPCInRange() {
        int playerX = Player.getPlayerPosX();
        int playerY = Player.getPlayerPosY();
        int npcX = GamePlay.accessNPC().getNpcPosX();
        int npcY = GamePlay.accessNPC().getNpcPosY();
        char playerDirection = Player.getCurrentDirection();
        char npcDirection = GamePlay.accessNPC().getDirection();

        double distance = Math.sqrt(Math.pow(playerX - npcX, 2) + Math.pow(playerY - npcY, 2));
        double interactionThreshold = 50.0;

        return (distance <= interactionThreshold) &&
                (playerDirection == 'w' && npcDirection == 's' ||
                 playerDirection == 's' && npcDirection == 'w' ||
                 playerDirection == 'a' && npcDirection == 'd' ||
                 playerDirection == 'd' && npcDirection == 'a');
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
                if (pressTime != 0) {
                    long releaseTime = java.lang.System.currentTimeMillis();
                    long elapsedTime = releaseTime - pressTime;
                    pressTime = 0;

                    Collectable collectable = Inventory.accessHotBar();
                    if (collectable != null && collectable instanceof Weapon) {
                        Weapon weapon = (Weapon) collectable;
                        if (GamePlay.hittable()) {
                            if (elapsedTime > 1500) {
                                weapon.use(true, (int) elapsedTime);
                                java.lang.System.out.println("Special attack");

                            } else {
                                if (weapon instanceof ShortSword) {
                                    ShortSword shortSword = (ShortSword) weapon;
                                    shortSword.criticalHits();
                                    shortSword.use(true, (int) elapsedTime);
                                    shortSword.resetDamage();


                                }
                                // Normal attack
                                weapon.use(false, (int) elapsedTime);


                            }
                        }else { weapon.swing(true, (int) elapsedTime);
                        if (weapon instanceof ShortSword) {
                            ShortSword shortSword = (ShortSword) weapon;
                            shortSword.criticalHits();
                            java.lang.System.out.println("i swung");
                        }
                    }
                    }
                        else if (collectable instanceof Item) {
                    Item item = (Item) collectable;
                    item.use(true, (int) elapsedTime);
                }
                    }
                break;
                    }
                    }





    public static void setReadable(boolean readable) {

        instance.readKeys = readable;
    }
}









