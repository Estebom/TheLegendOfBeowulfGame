import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * chest class is responsible for giving out items to players
 * @author Esteban Rodriguez
 */
public class Chest extends ChestImages implements Serializable {


    private String name;

    private int chestPosX;
    private int chestPosY;
    private char direction = ' ';

    private Timer animationTimer;
    private boolean isMoving;
    private boolean closedState = true;

    private ArrayList<Collectable> chestInventory;


    public Chest(String name, char direction) {

        this.name = name;
        this.direction = direction;
        chestInventory = new ArrayList<>();

        ImageIcon icon;
        switch (this.direction) {
            case 'a':
                icon = this.leftFacingChestClose; // Assuming leftFacingChestClose is the icon for 'a'
                break;
            case 'd':
                icon = this.rightFacingChestClose; // Assuming rightFacingChestClose is the icon for 'd'
                break;
            // Add other cases if needed
            default:
                icon = null;
                break;
        }

        if (icon != null) {
            this.setIcon(icon);
        }
    }
    private void resetTimer(){

        this.animationTimer = new Timer(150, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {updateAnimation();
            }
        });
    }

    public void updateAnimation(){

        if (isMoving) {
            closedState = !closedState;
            this.updateAnimation(this.direction, closedState);
        } else {
            animationTimer.stop();
        }
    }
    public Collectable openChest() {

        int range = (1 - 5) + 1;
        int decision = (int) (Math.random() * range) + 1;

        Collectable collectable = chestInventory.get(decision);
        return collectable;

    }

    public void addCollectable(Collectable collectable){
        chestInventory.add(collectable);


    }
    public int getChestPosX(){
        return this.chestPosX;

    }
    public int getChestPosY(){
        return this.chestPosY;
    }
    public char getDirection(){
        return this.direction;
    }



}