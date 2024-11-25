package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public int hasKey = 0; // How Many Keys Player Currently Has

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize/2);
        screenY = gp.screenHeight / 2 - (gp.tileSize/2);

        // PLAYER COLLISION AREA SETTINGS
        solidArea = new Rectangle();
        solidArea.x = 26;
        solidArea.y = 36;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 12;
        solidArea.height = 24;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues() {

        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 22;
        speed = 3;
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

        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) { 

        // UPDATE LOOP
        if(keyH.upPressed == true) {
            direction = "up";
        }
        else if(keyH.downPressed == true) {
            direction = "down";
        }
        else if(keyH.leftPressed == true) {
            direction = "left";
        }
        else if(keyH.rightPressed == true) {
            direction = "right";
        }

        // CHECK TILE COLLISION
        collisionOn = false;
        gp.cChecker.checkTile(this);

        // CHECK OBJECT COLLISION
        int objIndex = gp.cChecker.checkObject(this, true);
        pickUpObject(objIndex);

        // IF COLLISION IS FALSE, PLAYER CAN MOVE
        if(collisionOn == false) {

            switch(direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
        }
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

    public void pickUpObject(int i) {

        if(i != 999) {

            String objectName = gp.obj[i].name;

            switch(objectName) {
                case "Key":
                    gp.playSFX(1);
                    hasKey++;
                    gp.obj[i] = null;
                    gp.ui.showMessage("Key Found");
                        break; 
                case "Door":
                    gp.playSFX(3);
                    if(hasKey > 0) {
                        gp.obj[i] = null; 
                        gp.ui.showMessage("Door Opened");   
                        hasKey--;
                    }
                    else{
                        gp.ui.showMessage("Key Required");
                    }
                        break;
                case "Speed_Potion":
                    gp.playSFX(2);
                    speed += 2; // SPEED POTION
                    gp.obj[i] = null;
                    gp.ui.showMessage("Movement ++");
                        break;
                case "Chest":
                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                    gp.playSFX(4);
                        break;
            }

        }

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

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

        // PLAYER COLLISION BOX CHECKER - Uncomment to Enable

        //g2.setColor(Color.red);
        //g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
    }
}
