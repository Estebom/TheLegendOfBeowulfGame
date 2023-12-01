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
    private Movement movement;
    private Inventory inventory;

    private static KeyPad instance;
    private MainDisplay mainDisplay;
    private GamePlay gamePlay;

    private KeyPad(){
        this.interaction = new Interaction();
        this.attack = new Attack();
        this.movement = new Movement();
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
        switch (e.getKeyChar()){

            case 'w':
            case 'a':
            case 's':
            case 'd':
                this.movement.playerMove(e.getKeyChar());
                break;
            case 'e':
                java.lang.System.out.println("interact");
                break;
            case 'o':
                java.lang.System.out.println("attack");
                break;
            case 'p':
                java.lang.System.out.println("special attack");
                break;
            case 'm':
                java.lang.System.out.println("inventory open");
                break;
            case 'n':
                java.lang.System.out.println("dropped item");
                break;





        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }



}
