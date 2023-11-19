import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    public NewGame(MainDisplay mainDisplay){

        this.mainDisplay = mainDisplay;
        gamePlay = new GamePlay(mainDisplay);

        this.setPreferredSize(new Dimension(800, 675));
        this.setLayout(new GridBagLayout());


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
        goBack.addActionListener(e -> mainDisplay.showMainMenu());
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

    public void createPlayer(String name){

        Player player = Player.getInstance();
        player.setPlayerName(name);
    }

    ActionListener buttonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            switch (e.getActionCommand()){

                case "START":
                    createPlayer(name.getText());
                    gamePlay.initialize();
                    mainDisplay.showGamePlay();
                    break;

                case "MainMenu":
                    mainDisplay.showMainMenu();
                    break;


            }


        }
    };



}
