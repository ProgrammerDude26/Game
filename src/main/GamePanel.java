package main;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{

    // SCREEN SETTINGS

    final int originalTileSize = 16; // 16x16 tile 
    final int scale = 3; // scales size for bigger resolution

    final int tileSize = originalTileSize * scale; // 48x48 tile because 16 * 3
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels width
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels height

    Thread gameThread; // Once Thread Starts it keeps program running until stopped.

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // Can improve rendering performance

    }

    public void startGameThread(){

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        //throw new UnsupportedOperationException("Unimplemented method 'run'");

    }
}
