package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import entity.Player;
import obj.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
    // SCREEN SETTINGS
    final int originalTileSize = 32; // 16x16 tile 
    final int scale = 2; // scales size for bigger resolution
    public final int tileSize = originalTileSize * scale; // 48x48 tile because 16 * 3
    public final int maxScreenCol = 50;
    public final int maxScreenRow = 50;
    public final int screenWidth = 768; //tileSize * maxScreenCol; // 768 pixels width
    public final int screenHeight = 576; //tileSize * maxScreenRow; // 576 pixels height

    //WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    //public final int worldWidth = tileSize * maxWorldCol; // UNUSED?
    //public final int worldHeight = tileSize * maxWorldRow; // UNUSED?

    // FPS
    int FPS = 60;

    // SYSTEM
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);
    Sound sfx = new Sound();
    Sound music = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;

    // ENTITY & OBJECT
    public Player player = new Player(this,keyH);
    public SuperObject obj[] = new SuperObject[10]; // Can Display UP TO 10 objects at the same time can change

    // GAME STATE
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // Can improve rendering performance
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {
        aSetter.setObject();
        playMusic(0);
        playAmbience(5);
        gameState = playState;
    }

    // GAME THREAD
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
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
                // System.out.println("FPS: " + drawCount); // FPS Counter
                drawCount = 0;
                timer = 0;
            }
        }
    }
    public void update() {

        if(gameState == playState) {
            player.update();
        }
        if(gameState == pauseState) {
            // Nothing for now 
        }

    }
    public void paintComponent(Graphics g) {    
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        // DEBUG
        long drawStart = 0;
        if(keyH.checkDrawTime == true){
            drawStart = System.nanoTime();   
        }

        //TILE
        tileM.draw(g2);

        //OBJECT
        for(int i = 0;  i < obj.length; i++) {
            if(obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }

        //PLAYER 
        player.draw(g2);

        //UI
        ui.draw(g2);

        // DEBUG
        if(keyH.checkDrawTime == true){
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.lightGray);
            g2.drawString("Draw Time: " + passed, 550, 30);
            System.out.println("Draw Time: " + passed);
        }
        
        g2.dispose();
    }

    public void playMusic(int i) {

        music.setFile(i);
        music.play();
    }

    public void playAmbience(int i) {

        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {

        music.stop();
    }
    public void playSFX(int i) {

        sfx.setFile(i);
        sfx.play();
    }
}