import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

public class GamePlay extends JPanel {

    private MainDisplay mainDisplay ;

    private KeyPad keyPad;





    public GamePlay(MainDisplay mainDisplay){

            this.mainDisplay = mainDisplay;
            this.keyPad = KeyPad.getInstance();
            this.setPreferredSize(new Dimension(1080,1920));
            this.setLayout(new BorderLayout());
            this.setFocusable(true);
            this.addKeyListener(keyPad);


    }

    public void initialize(){
        this.requestFocusInWindow();
    }



}
