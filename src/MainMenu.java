import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is the MainMenu interface for users
 * @author Esteban Rodriguez
 */
public class MainMenu extends JPanel{
    private MainDisplay mainDisplay;

    JButton newGameButton;
    JButton loadGameButton;
    JButton settingButton;
    JButton exitGameButton;

    JPanel buttonLayout;

    JPanel centerPanel;


    public MainMenu(MainDisplay mainDisplay){

        this.mainDisplay = mainDisplay;

        this.setPreferredSize(new Dimension(2560, 1440));
        this.setLayout(new BorderLayout());




        newGameButton = new JButton("New Game");
        newGameButton.setPreferredSize(new Dimension(600, 150));
        newGameButton.setMaximumSize(new Dimension(600, 150)); // Set maximum size


        loadGameButton = new JButton("Load Game");
        loadGameButton.setPreferredSize(new Dimension(600, 150));
        loadGameButton.setMaximumSize(new Dimension(600, 150)); // Set maximum size

        settingButton = new JButton("Settings");
        settingButton.setPreferredSize(new Dimension(600, 150));
        settingButton.setMaximumSize(new Dimension(600, 150)); // Set maximum size

        exitGameButton = new JButton("Exit Game");
        exitGameButton.setPreferredSize(new Dimension(600, 150));
        exitGameButton.setMaximumSize(new Dimension(600, 150)); // Set maximum size


        newGameButton.setActionCommand("NewGame");
        loadGameButton.setActionCommand("LOAD_GAME");
        settingButton.setActionCommand("SETTINGS");
        exitGameButton.setActionCommand("EXIT");

        newGameButton.addActionListener(e -> mainDisplay.showNewGame());
        loadGameButton.addActionListener(buttonListener);
        settingButton.addActionListener(buttonListener);
        exitGameButton.addActionListener(buttonListener);

        buttonLayout = new JPanel();
        buttonLayout.setLayout(new BoxLayout(buttonLayout, BoxLayout.Y_AXIS));
        buttonLayout.add(Box.createVerticalGlue());
        buttonLayout.add(newGameButton);

        //adds spacing between buttons
        buttonLayout.add(Box.createRigidArea(new Dimension(0, 25))); // 10-pixel vertical space
        buttonLayout.add(loadGameButton);
        buttonLayout.add(Box.createRigidArea(new Dimension(0, 25))); // 10-pixel vertical space
        buttonLayout.add(settingButton);
        buttonLayout.add(Box.createRigidArea(new Dimension(0, 25))); // 10-pixel vertical space
        buttonLayout.add(exitGameButton);
        buttonLayout.add(Box.createVerticalGlue());

        centerPanel = new JPanel(new GridBagLayout());
        centerPanel.add(buttonLayout);

        this.add(centerPanel, BorderLayout.CENTER);
        this.setVisible(true);



    }
    ActionListener buttonListener  = new ActionListener() {
        @Override
        public void actionPerformed (ActionEvent e){
            switch (e.getActionCommand()){
                case "NewGame":
                    java.lang.System.out.println("New Game started");
                    mainDisplay.showNewGame();
                    break;
                case "LOAD_GAME":
                    java.lang.System.out.println("Load Game clicked");

                    break;
                case "SETTINGS":
                    mainDisplay.showSettings();
                    java.lang.System.out.println("Settings opened");

                    break;
                case "EXIT":
                    java.lang.System.exit(1);
                    break;

            }
            MainMenu.this.revalidate();
            MainMenu.this.repaint();

        }
    };


}
