import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * This class is used to handle keyboard inputs during GamePlay
 * @authors Esteban Rodriguez & Alfonso Avila
 */
public class KeyPad extends KeyAdapter {

    // Instance variables
    private Interaction interaction;
    private static KeyPad instance;
    private long pressTime = 0;
    private boolean readKeys = true;
    private int inventoryToggle = 1;

    // Private constructor initializing the Interaction object
    private KeyPad() {
        this.interaction = new Interaction();
    }

    // Static method to set up an escape key binding
    public static void setupEscapeKeyBinding(JComponent component, Runnable actionToPerform) {
        // Input validation
        if (component == null) {
            throw new IllegalArgumentException("Component cannot be null");
        }

        // Set up key binding for the Escape key
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

    // Static method to set up an 'M' key binding
    public static void setupMKeyBinding(JComponent component, Runnable actionToPerform) {
        // Input validation
        if (component == null) {
            throw new IllegalArgumentException("Component cannot be null");
        }

        // Set up key binding for the 'M' key
        KeyStroke mKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_M, 0);
        InputMap inputMap = component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = component.getActionMap();

        inputMap.put(mKeyStroke, "openInventory");
        actionMap.put("openInventory", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionToPerform.run();
            }
        });
    }

    // Static method to set up a 'Q' key binding
    public static void setupQKeyBinding(JComponent component, Runnable actionToPerform) {
        // Input validation
        if (component == null) {
            throw new IllegalArgumentException("Component cannot be null");
        }

        // Set up key binding for the 'Q' key
        KeyStroke qKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0);
        InputMap inputMap = component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = component.getActionMap();

        inputMap.put(qKeyStroke, "openInteraction");
        actionMap.put("openInteraction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionToPerform.run();
            }
        });
    }

    // Static method to get or create an instance of KeyPad
    public static KeyPad getInstance(GamePlay gamePlay) {
        if (instance == null) {
            instance = new KeyPad();
        }
        return instance;
    }

    // Method to handle key pressed events
    @Override
    public void keyPressed(KeyEvent e) {
        if (readKeys) {
            switch (e.getKeyCode()) {
                // Handling numeric key presses to set collectable in use
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

                // Handling movement keys
                case KeyEvent.VK_W:
                case KeyEvent.VK_A:
                case KeyEvent.VK_S:
                case KeyEvent.VK_D:
                    Player.move(e.getKeyChar());
                    break;

                // Handling 'E' key press
                case KeyEvent.VK_E:
                    if (Inventory.accessHotBar() instanceof Weapon) {
                        Player.startAttack();
                        if (pressTime == 0) { // Only set if not already pressed
                            pressTime = java.lang.System.currentTimeMillis();
                        }
                    }
                    break;

                // Handling 'Q' key press
                case KeyEvent.VK_Q:
                    java.lang.System.out.println("Interact");
                    handleChestInteraction();
                    handleInteraction();
                    break;

                // Handling 'M' key press
                case KeyEvent.VK_M:
                    Inventory.inventoryToggle();
                    if (inventoryToggle == 1) {
                        GamePlay.pauseGame();
                        inventoryToggle++;
                    } else {
                        GamePlay.startGame();
                        inventoryToggle = 1;
                    }
                    break;

                // Handling 'N' key press
                case KeyEvent.VK_N:
                    java.lang.System.out.println("dropped item");
                    break;

                // Handling Escape key press
                case KeyEvent.VK_ESCAPE:
                    GamePlay.showGameMenu();
                    GamePlay.pauseGame();
                    this.readKeys = false;
                    break;
            }
        }
    }

    // Method to handle 'Q' key press interaction
    private void handleInteraction() {
        if (isNPCInRange()) {
            java.lang.System.out.println("interacting");
            GamePlay.showInteraction();
        }
    }
    public void handleChestInteraction() {
        if(isChestInRange()){
            instance.interaction.openChest();
        }
    }

    public static boolean isChestInRange() {
        int playerX = Player.getPlayerPosX();
        int playerY = Player.getPlayerPosY();
        char playerDirection = Player.getCurrentDirection();
        ArrayList<Chest> chests = GamePlay.accessChests();
        double interactionThreshold = 50.0;

        for (Chest chest : chests) {
            int chestX = chest.getChestPosX();
            int chestY = chest.getChestPosY();
            double distance = Math.sqrt(Math.pow(playerX - chestX, 2) + Math.pow(playerY - chestY, 2));

            if (distance <= interactionThreshold) {
                char chestDirection = chest.getDirection();
                boolean isFacingEachOther =
                        (playerDirection == 'w' && chestDirection == 's') ||
                                (playerDirection == 's' && chestDirection == 'w') ||
                                (playerDirection == 'a' && chestDirection == 'd') ||
                                (playerDirection == 'd' && chestDirection == 'a');


                if (isFacingEachOther) {
                    instance.interaction.setChest(chest);
                    return true;
                }
            }
        }
        return false;
    }

    // Method to check if an NPC is in interaction range
    private boolean isNPCInRange() {
        // Getting player and NPC positions and directions
        int playerX = Player.getPlayerPosX();
        int playerY = Player.getPlayerPosY();
        int npcX = GamePlay.accessNPC().getNpcPosX();
        int npcY = GamePlay.accessNPC().getNpcPosY();
        char playerDirection = Player.getCurrentDirection();
        char npcDirection = GamePlay.accessNPC().getDirection();

        // Calculating distance between player and NPC
        double distance = Math.sqrt(Math.pow(playerX - npcX, 2) + Math.pow(playerY - npcY, 2));
        double interactionThreshold = 50.0;

        // Checking if the distance and directions meet criteria for interaction
        return (distance <= interactionThreshold) &&
                (playerDirection == 'w' && npcDirection == 's' ||
                        playerDirection == 's' && npcDirection == 'w' ||
                        playerDirection == 'a' && npcDirection == 'd' ||
                        playerDirection == 'd' && npcDirection == 'a');
    }

    // Method to handle key released events
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            // Handling movement key releases
            case KeyEvent.VK_W:
            case KeyEvent.VK_A:
            case KeyEvent.VK_S:
            case KeyEvent.VK_D:
                Player.stopMoving();
                break;

            // Handling 'E' key release
            case KeyEvent.VK_E:
                if (pressTime != 0) {
                    long releaseTime = java.lang.System.currentTimeMillis();
                    long elapsedTime = releaseTime - pressTime;
                    pressTime = 0;

                    // Handling 'E' key release for different types of collectables
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
                                weapon.use(false, (int) elapsedTime);
                            }
                        } else {
                            weapon.swing(true, (int) elapsedTime);
                            if (weapon instanceof ShortSword) {
                                ShortSword shortSword = (ShortSword) weapon;
                                shortSword.criticalHits();
                                java.lang.System.out.println("i swung");
                            }
                        }
                    } else if (collectable instanceof Item) {
                        Item item = (Item) collectable;
                        item.use(true, (int) elapsedTime);
                    }
                }
                break;
        }
    }

    // Method to set the readability of the keypad
    public static void setReadable(boolean readable) {
        instance.readKeys = readable;
    }
}
