//Esteban Rodriguez & Alfonso Avila

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class NPC extends JLabel{

    private int scaleWidth = 100;
    private int scaleHeight = 100;
    private JPanel interactionPanel;
    private CardLayout cardLayout;
    private static ImageIcon frontFacingLeftIcon;

    private static ImageIcon backFacingLeftIcon;

    private static ImageIcon leftFacingStillIcon;


    private static ImageIcon rightFacingStillIcon;

    private String nameNPC;
    private ArrayList<String> dialogueList = new ArrayList<>();
    private ArrayList<Collectable> npcInventory = new ArrayList<>();
    private int dialougeSpot;
    private String introLine;
    private Image scaledImage = null;
    private int npcPosX;
    private int npcPosY;
    private char direction = ' ';
    public NPC(String nameNPC, char direction){

        this.nameNPC = nameNPC;
        this.direction = direction;

        dialougeSpot = 0;


        frontFacingLeftIcon = loadScaledIcon("resources/images/FrontFacingBeowulf.png");
        backFacingLeftIcon = loadScaledIcon("resources/images/BackLeft.png");
        leftFacingStillIcon = loadScaledIcon("resources/images/LeftFacingStanding.png");

        rightFacingStillIcon = loadScaledIcon("resources/images/RightFacingStanding.png");



        ImageIcon icon = null;
        switch (this.direction){
            case 'a': icon = leftFacingStillIcon;
                break;
            case 'd': icon = rightFacingStillIcon;
                break;
            case 'w': icon = backFacingLeftIcon;
                break;
            case 's': icon = frontFacingLeftIcon;
                break;
        }
        if(icon != null){
            this.setIcon(icon);
        }

        this.setPreferredSize(new Dimension(scaleWidth, scaleHeight));

        interactionPanel = new JPanel();
        cardLayout = new CardLayout();
        JLabel npcName = new JLabel(this.nameNPC);
        JLabel initialMessage = new JLabel(this.introLine);

        interactionPanel.setSize(new Dimension(300,200));
        interactionPanel.setBackground(Color.BLUE);
        interactionPanel.setLayout(cardLayout);

        JPanel interactionMenu = new JPanel();
        JButton openBuy = new JButton("buy");
        JButton openSell = new JButton("sell");
        JButton openSpeak = new JButton("speak");
        JButton close = new JButton("close");

        close.addActionListener(e -> interactionPanel.setVisible(false));
        close.addActionListener(e -> KeyPad.setReadable(true));
        openBuy.addActionListener(e -> showBuyPanel());
        openSell.addActionListener(e -> showSellPanel());
        openSpeak.addActionListener(e -> showSpeakPanel());
        interactionMenu.add(openBuy);
        interactionMenu.add(openSell);
        interactionMenu.add(openSpeak);
        interactionMenu.add(close);
        interactionMenu.add(npcName);
        interactionMenu.add(initialMessage);
        interactionMenu.setVisible(true);

        JPanel buyView = new JPanel();
        JLabel buying = new JLabel("here to buy?");
        JButton goBackFromBuy = new JButton("return");
        goBackFromBuy.addActionListener(e -> showInteractionMenu());
        buyView.add(buying);
        buyView.add(goBackFromBuy);

        JPanel sellView = new JPanel();
        JLabel selling = new JLabel("here to sell?");
        JButton goBackFromSell = new JButton("return");
        goBackFromSell.addActionListener(e -> showInteractionMenu());
        sellView.add(selling);
        sellView.add(goBackFromSell);

        JPanel speakView = new JPanel();
        JLabel speaking = new JLabel("talking");
        JButton goBackFromSpeak = new JButton("return");
        goBackFromSpeak.addActionListener(e -> showInteractionMenu());
        speakView.add(speaking);
        speakView.add(goBackFromSpeak);

        interactionPanel.add(interactionMenu, "interactionMenu");
        interactionPanel.add(buyView, "buyView");
        interactionPanel.add(sellView, "sellView");
        interactionPanel.add(speakView, "speakView");
        showInteractionMenu();
        interactionPanel.setVisible(true);

    }
    private ImageIcon loadScaledIcon(String path) {
        BufferedImage img = null;
        try (InputStream is = getClass().getResourceAsStream(path)) {
            if (is != null) {
                img = ImageIO.read(is);
            } else {
                // Consider logging this or throwing an exception
                System.err.println("Could not load image at path: " + path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (img != null) {
            Image scaledImage = img.getScaledInstance(scaleWidth, scaleHeight, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        } else {
            return new ImageIcon(); // Return an empty icon in case of failure
        }
    }

    public void addNPCInventory(Collectable collectable){

        npcInventory.add(collectable);

    }

    public Collectable buy(int selectedCollectable){

        Collectable collectable = npcInventory.get(selectedCollectable);
        npcInventory.remove(selectedCollectable);
        return collectable;

    }

    public void sell(Collectable collectable){

            Inventory.addCurrency(collectable.getPrice());
            Inventory.removeCollectable(collectable);
    }

    public String speak(){


        String dialougeLine = dialogueList.get(dialougeSpot);
        this.dialougeSpot += 1;

        return dialougeLine;
    }
    public void setPostion(int npcPosX, int npcPosY){
        this.npcPosX = npcPosX;
        this.npcPosY = npcPosY;
    }

    public char getDirection(){
        return this.direction;
    }

    public JPanel showInteractionPanel(){
       return this.interactionPanel;

    }

    public void showInteractionMenu(){
        cardLayout.show(interactionPanel, "interactionMenu");


    }
    public void showBuyPanel() {
        cardLayout.show(interactionPanel, "buyView");


    }
    public void showSellPanel() {
        cardLayout.show(interactionPanel, "sellView");


    }
    public void showSpeakPanel() {
        cardLayout.show(interactionPanel, "speakView");


    }
    public int getNpcPosX(){
        return this.npcPosX;
    }
    public int getNpcPosY(){
        return this.npcPosY;
    }


    public void setIntroLine(String introLine){
        this.introLine = introLine;
    }


}
