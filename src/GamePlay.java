import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

/**
 * handles all game logic
 * @author Esteban Rodriguez
 */
public class GamePlay extends JPanel {

    private MainDisplay mainDisplay ;

    private KeyPad keyPad;

    private PlayerSprite playerSprite;
    private  JLayeredPane layeredPane;



    public GamePlay(MainDisplay mainDisplay){

            this.mainDisplay = mainDisplay;
            this.keyPad = KeyPad.getInstance();
            this.setPreferredSize(new Dimension(1080,1920));
            this.setLayout(new BorderLayout());
            this.setFocusable(true);
            this.addKeyListener(keyPad);
            this.setBackground(Color.black);


            layeredPane = new JLayeredPane();
            layeredPane.setPreferredSize(new Dimension(1080, 1920));
            layeredPane.setLayout(null); // Set layout to null

            playerSprite = PlayerSprite.getInstance();
            playerSprite.setBounds(750, 750, 100, 100); // Set initial position and size
            layeredPane.add(playerSprite);

            this.add(layeredPane, BorderLayout.CENTER);



    }

    /**
     * sets all keyboard inputs to this panel
     */
    public void initialize(){
        this.requestFocusInWindow();
    }



}
