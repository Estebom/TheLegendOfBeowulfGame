import javax.swing.*;
import java.awt.*;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.


/*
Class for the main display of the game
@author Esteban Rodriguez
 */
public class MainDisplay extends JFrame {


    public MainDisplay(){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        //this.setUndecorated(true);  //will be used during real implementation


        this.setSize(new Dimension(2560, 1440));
        this.setLayout((new BorderLayout()));
        this.pack();




        this.setVisible(true);

    }

    public static void main(String[] args) {

        MainDisplay mainDisplay = new MainDisplay();
        MainMenu mainMenu = new MainMenu();
        mainDisplay.add(mainMenu);
        mainDisplay.pack();
        mainDisplay.setVisible(true);
        System.out.println(mainMenu.centerPanel.getPreferredSize());
    }

}