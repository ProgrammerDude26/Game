package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
    // FPS
    int FPS = 60;
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    // Player Default Position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4; // Player Speed = 4 px

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // Can improve rendering performance
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    // GAME THREAD
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        double drawInterval = 1000000000/ FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if(timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }
    public void update() {
        if(keyH.upPressed == true) {
            playerY = playerY - playerSpeed;
        }
        else if(keyH.downPressed == true) {
            playerY = playerY + playerSpeed;
        }
        else if(keyH.leftPressed == true) {
            playerX = playerX - playerSpeed;
        }
        else if(keyH.rightPressed == true) {
            playerX = playerX + playerSpeed;
        }

    }
    public void paintComponent(Graphics g) {    
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose();
    }
}