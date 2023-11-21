import javax.swing.*;
import java.awt.*;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.


/*
Class for the main display of the game
@author Esteban Rodriguez
*/

//public class MainDisplay extends JFrame {
//    private MainMenu mainMenu;
//    private NewGame newGame;
//
//    private GamePlay gamePlay;
//    public MainDisplay(){
//
//        mainMenu = new MainMenu(this);
//        newGame = new NewGame(this);
//        gamePlay = new GamePlay(this);
//
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setResizable(false);
//        //this.setUndecorated(true);  //will be used during real implementation
//
//
//        this.setSize(new Dimension(2560, 1440));
//        this.setLayout((new CardLayout()));
//
//        this.add(mainMenu, "MainMenu");
//        this.add(newGame, "NewGame");
//        this.add(gamePlay, "START");
//
//        showMainMenu();
//        this.setVisible(true);
//
//    }

public class MainDisplay extends JFrame {
    private MainMenu mainMenu;
    private NewGame newGame;
    private GamePlay gamePlay;

    public MainDisplay() {
        mainMenu = new MainMenu(this);
        newGame = new NewGame(this);
        gamePlay = new GamePlay(this);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        // Set size for 1080p resolution
        this.setSize(new Dimension(1920, 1080));
        this.setLayout(new CardLayout());

        this.add(mainMenu, "MainMenu");
        this.add(newGame, "NewGame");
        this.add(gamePlay, "START");

        showMainMenu();
        this.setVisible(true);
    }


    public void showMainMenu(){
        ((CardLayout)this.getContentPane().getLayout()).show(this.getContentPane(), "MainMenu");
    }
    public  void showNewGame(){

        ((CardLayout)this.getContentPane().getLayout()).show(this.getContentPane(),"NewGame");
    }
    public void showGamePlay(){

        ((CardLayout)this.getContentPane().getLayout()).show(this.getContentPane(), "START");

    }






}