package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues() {

        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }
    public void getPlayerImage() {

        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/player/PlayerUp1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/player/PlayerUp2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/player/PlayerDown1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/player/PlayerDown2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/player/PlayerLeft1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/player/PlayerLeft2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/player/PlayerRight1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/player/PlayerRight2.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update() {

        //if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) { 
        //} // Move the Below Update Loop Inside Here to Stop Animation when not presssing key

        // UPDATE LOOP
        if(keyH.upPressed == true) {
            direction = "up";
            y = y - speed;
        }
        else if(keyH.downPressed == true) {
            direction = "down";
            y = y + speed;
        }
        else if(keyH.leftPressed == true) {
            direction = "left";
            x = x - speed;
        }
        else if(keyH.rightPressed == true) {
            direction = "right";
            x = x + speed;
        }

        spriteCounter++;

        if(spriteCounter > 10) { // Sprite Changer Speed
            if(spriteNum == 1) {
                spriteNum = 2;
            }
            else if(spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
        // END UPDATE LOOP
    }
    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch(direction) {
        case "up":
            if(spriteNum == 1) {
                image = up1;
            }
            if(spriteNum == 2) {
                image = up2;
            }
            break;
        case "down":
            if(spriteNum == 1) {
                image = down1;
            }
            if(spriteNum == 2) {
                image = down2;
            }
            break;
        case "left":
            if(spriteNum == 1) {
                image = left1;
            }
            if(spriteNum == 2) {
                image = left2;
            }
            break;
        case "right":
            if(spriteNum == 1) {
                image = right1;
            }
            if(spriteNum == 2) {
                image = right2;
            }
            break;
        }

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

    }
}