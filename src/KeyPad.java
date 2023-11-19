import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class KeyPad extends KeyAdapter {

    private static KeyPad instance;
    private MainDisplay mainDisplay;
    private GamePlay gamePlay;

    private KeyPad(){}



    public static KeyPad getInstance(){

        if(instance == null){
            instance = new KeyPad() {
                @Override
                public void keyPressed(KeyEvent e) {
                    switch (e.getKeyChar()){

                        case 'w':
                            java.lang.System.out.println("foward");
                            break;
                        case 'a':
                            java.lang.System.out.println("left");
                            break;
                        case 's':
                            java.lang.System.out.println("back");
                            break;
                        case 'd':
                            java.lang.System.out.println("right");
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
                };
            }
            return instance;
        }


    public void keyPressed(String next) {
    }
}
