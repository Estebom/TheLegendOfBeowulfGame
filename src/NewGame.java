import javax.swing.*;
import java.awt.*;

public class NewGame extends JPanel {

    JTextField name;
    JButton easyButton;
    JButton mediumButton;

    JButton hardButton;

    JButton startButton;

    JPanel newGameFeatures;
    JPanel difLayout;
    public NewGame(){

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
        newGameFeatures.add(startButton, gbcFeatures);



        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.NONE;



        this.add(newGameFeatures, gbc);
        this.setVisible(true);

    }



}
