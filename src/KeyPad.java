import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

/**
 * used to handle keyboard inputs during GamePlay
 * @author Esteban
 */
public class KeyPad extends KeyAdapter {

    private Interaction interaction;
    private Attack attack;
    private PlayerSprite playerSprite;
    private Inventory inventory;

    private static KeyPad instance;
    private MainDisplay mainDisplay;
    private GamePlay gamePlay;

    private KeyPad(){
        this.interaction = new Interaction();
        this.attack = new Attack();
        this.inventory = Inventory.getInstance();
    }



    public static KeyPad getInstance(){

        if(instance == null){
            instance = new KeyPad();
            }
            return instance;
        }
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W:
            case KeyEvent.VK_A:
            case KeyEvent.VK_S:
            case KeyEvent.VK_D:
                PlayerSprite.getInstance().move(e.getKeyChar());
                break;
            case KeyEvent.VK_E:
                java.lang.System.out.println("use collectable");
                break; // Added break here
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
                // Add your action for ESC key here
                break;

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
        }
    }



}
