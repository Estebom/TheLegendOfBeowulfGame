import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * For the creation of a new game state, dispays new game interface
 * @author Esteban Rodriguez
 */
public class NewGame extends JPanel {

    private GamePlay gamePlay;
    private MainDisplay mainDisplay;
    JTextField name;
    JButton easyButton;
    JButton mediumButton;

    JButton hardButton;

    JButton startButton;

    JButton goBack;

    JPanel newGameFeatures;
    JPanel difLayout;
    JLabel label;
    public NewGame(MainDisplay mainDisplay){

        this.mainDisplay = mainDisplay;
        gamePlay = new GamePlay(mainDisplay);

        this.setPreferredSize(new Dimension(800, 675));
        this.setLayout(new GridBagLayout());
        label = new JLabel();
        label.setBackground(Color.black);
        label.setPreferredSize(new Dimension(800,675));
        label.setOpaque(true);
        this.add(label);


        newGameFeatures = new JPanel(new GridBagLayout());
        GridBagConstraints gbcFeatures = new GridBagConstraints();




        difLayout = new JPanel();
        difLayout.setLayout(new FlowLayout());

        name = new JTextField("Player what is your name?");
        //name.setPreferredSize(new Dimension(100, 100));
        //name.setVisible(true);
        gbcFeatures.gridx = 0;
        gbcFeatures.gridy = 0;
        gbcFeatures.fill = GridBagConstraints.HORIZONTAL;
        gbcFeatures.weightx = 1.0;
        gbcFeatures.insets = new Insets(20, 20, 20, 20);
        name.setPreferredSize(new Dimension(400, 100)); // More typical text field size
        newGameFeatures.add(name, gbcFeatures);



        easyButton = new JButton("Easy");
        easyButton.setPreferredSize(new Dimension(250,100));

        mediumButton = new JButton("Medium");
        mediumButton.setPreferredSize(new Dimension(250,100));

        hardButton = new JButton("Hard");
        hardButton.setPreferredSize(new Dimension(250,100));



        difLayout.add(easyButton);
        difLayout.add(mediumButton);
        difLayout.add(hardButton);

        gbcFeatures.gridy++;
        newGameFeatures.add(difLayout, gbcFeatures);

        gbcFeatures.gridy++;
        startButton = new JButton("Start Your Adventure!");
        startButton.setPreferredSize(new Dimension(800, 100)); // Adjusted size
        startButton.setActionCommand("START");
        startButton.addActionListener(buttonListener);
        newGameFeatures.add(startButton, gbcFeatures);

        gbcFeatures.gridy++;
        goBack = new JButton("Go Back to Main Menu");
        goBack.setPreferredSize(new Dimension(50,50));
        goBack.setActionCommand("MainMenu");
        goBack.addActionListener(buttonListener);
        newGameFeatures.add(goBack, gbcFeatures);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.NONE;



        this.add(newGameFeatures, gbc);
        this.setVisible(true);

    }

    /**
     * creates Player instance
     * @param name string sets the Player instance's name
     */
    public void createPlayer(String name){

        Player player = Player.getInstance();
        player.setPlayerName(name);
        java.lang.System.out.println(player.getPlayerName());
    }
    public void updateLayout(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));

        // Update the black label size and position
        label.setBounds(0, 0, width, height); // Adjust the label to fit the new resolution

        // Update the size and position of other components if necessary
        // For example, adjust the preferred size of buttons and text field
        name.setPreferredSize(new Dimension(width / 2, 100)); // Example adjustment
        easyButton.setPreferredSize(new Dimension(width / 4, 100)); // Example adjustment
        mediumButton.setPreferredSize(new Dimension(width / 4, 100)); // Example adjustment
        hardButton.setPreferredSize(new Dimension(width / 4, 100)); // Example adjustment
        startButton.setPreferredSize(new Dimension(width, 100)); // Example adjustment

        // Revalidate and repaint the panel to apply changes
        this.revalidate();
        this.repaint();
    }


    ActionListener buttonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            switch (e.getActionCommand()){

                case "START":
                    createPlayer(name.getText());
                    mainDisplay.showGamePlay();

                    break;

                case "MainMenu":
                    name.setText("Player what is your name?");
                    mainDisplay.showMainMenu();
                    NewGame.this.setEnabled(false);

                    break;


            }


        }
    };



}
