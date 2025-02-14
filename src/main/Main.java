package main;

import javax.swing.JFrame;


public class Main {

    public static void main(String[] args) {
        
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false); // non-resizeable
        window.setTitle("RPG Game - Java"); // Title Screen 

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null); // Start Screen in Center
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}