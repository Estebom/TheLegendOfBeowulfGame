/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.legendofbeowulf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


/**
 *
 * @author iacopolenzi
 */
public class GameState implements Serializable {
    private int currentLevel;
    private static final String SAVE_PATH = "saves/";  
    public static final String DEFAULT_SAVE = "latestGameState";
    private static GameState instance = null;
    
    public static GameState getInstance(){

        if(instance == null){
            instance = new GameState();
        }
        return instance;
    }
    
    private GameState(){
        this.currentLevel = 0;
    }
    
        public static void loadGame(String saveName) {
        
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(SAVE_PATH + saveName))) {
            instance = (GameState) inputStream.readObject(); // Load the player object
            Player.replacePlayerInstance((Player) inputStream.readObject());
            
            
            System.out.println("Loaded Player Name: " + Player.getInstance().getPlayerName());
            System.out.println("Loaded Game Level: " + getInstance().currentLevel);

            System.out.println("Player data loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading player data: " + e.getMessage());
        }
    }
        
    public static void saveGame(String saveName){
        writeGameState(saveName);
        writeGameState(DEFAULT_SAVE);
    }    
        
    private static void writeGameState(String saveName) {
        File saveFile = new File(SAVE_PATH);
        saveFile.mkdirs(); //Ensures SAVE_PATH Directory exists
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(SAVE_PATH + saveName))) {
            outputStream.writeObject(getInstance()); // Save the Game state
            outputStream.writeObject(Player.getInstance()); // Save the entire player object
            System.out.println("Player data saved successfully.");
        } 

        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error saving player data: " + e.getMessage());
        }
    }
    
    public void nextLevel(){
        getInstance().currentLevel+=1;
    }
    
    public int currentLevel(){
        return getInstance().currentLevel;
    }
    
    public static void main(String[] args){
        //Testing Level saving
        GameState.getInstance().nextLevel();
        GameState.getInstance().nextLevel();
        GameState.getInstance().nextLevel();
        GameState.saveGame("test");
        GameState.getInstance().nextLevel();
        GameState.getInstance().nextLevel();
        GameState.loadGame("test");
        System.out.println(GameState.getInstance().currentLevel());
        
        
        //Testing player data saving 
        Player.getInstance().setPlayerName("Testnm");
        System.out.println(Player.getInstance().getHealth());
        Player.getInstance().setHealth(5);
        GameState.saveGame("test");
        Player.getInstance().setHealth(10);
        GameState.loadGame("test");
        System.out.println(Player.getInstance().getHealth());
    }
}

