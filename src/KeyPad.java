import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * used to handle keyboard inputs during GamePlay
 * @author Esteban
 */
public class KeyPad extends KeyAdapter {

    private Interaction interaction;
    private Attack attack;
    private PlayerSprite playerSprite;
    private Inventory inventory;
    private GamePlay gamePlay;

    private static KeyPad instance;



    private boolean readKeys = true;

    private KeyPad(GamePlay gamePlay){
        this.gamePlay = gamePlay;
        this.interaction = new Interaction();
        this.attack = new Attack(gamePlay.getCurrentTarget());
        this.inventory = Inventory.getInstance();
    }

    public void setupEscapeKeyBinding(JComponent component, Runnable actionToPerform) {
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



    public static KeyPad getInstance(GamePlay gamePlay){

        if(instance == null){
            instance = new KeyPad(gamePlay);
            }
            return instance;
        }
    @Override
    public void keyPressed(KeyEvent e) {
        if(readKeys == true) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_1:
                case KeyEvent.VK_2:
                case KeyEvent.VK_3:
                case KeyEvent.VK_4:
                case KeyEvent.VK_5:
                    int digit = e.getKeyChar() - '0';
                    inventory.setCollectableInUse(digit);
                    if(inventory.accessHotBar().getClass() == Weapon.class){
                        Weapon weapon = new Weapon(inventory.accessHotBar());
                        weapon.modifyPlayerDamage(true);
                    }
                case KeyEvent.VK_W:
                case KeyEvent.VK_A:
                case KeyEvent.VK_S:
                case KeyEvent.VK_D:
                    PlayerSprite.getInstance().move(e.getKeyChar());
                    break;
                case KeyEvent.VK_E:
                    java.lang.System.out.println("use collectable");
                    int t1 = (int) java.lang.System.currentTimeMillis();
                    inventory.accessHotBar().use(false, t1);
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

                    gamePlay.showGameMenu();

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
                PlayerSprite.getInstance().stopMoving();
                break;
            case KeyEvent.VK_E:
                int t2 = (int) java.lang.System.currentTimeMillis();
                if (gamePlay.hittable() == true) {
                    if ((playerSprite.getCurrentDirection() == 'w' && playerSprite.getCurrentDirection() == 's')
                            || (playerSprite.getCurrentDirection() == 'd' && playerSprite.getCurrentDirection() == 'a')
                            || (playerSprite.getCurrentDirection() == 'a' && playerSprite.getCurrentDirection() == 'd')
                            || (playerSprite.getCurrentDirection() == 's' && playerSprite.getCurrentDirection() == 'w')) {

                        Attack attack1 = new Attack( gamePlay.getCurrentTarget());
                        inventory.accessHotBar().use(true, t2);
                    }
                }
        }
    }

    public void setReadable(boolean readable){
        this.readKeys = readable;
    }

}
