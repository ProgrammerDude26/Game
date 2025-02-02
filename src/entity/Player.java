package entity;

//import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{

    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {

        super(gp);

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

        // PLAYER STATUS
        maxLife = 10; // 6 Life is 3 Full Hearts
        life = maxLife;
    }
    public void getPlayerImage() {

        up1 = setup("/res/player/PlayerUp1");
        up2 = setup("/res/player/PlayerUp2");
        down1 = setup("/res/player/PlayerDown1");
        down2 = setup("/res/player/PlayerDown2");
        left1 = setup("/res/player/PlayerLeft1");
        left2 = setup("/res/player/PlayerLeft2");
        right1 = setup("/res/player/PlayerRight1");
        right2 = setup("/res/player/PlayerRight2");

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
        
        // CHECK NPC COLLISION
        int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
        interactNPC(npcIndex);

        // CHECK EVENT
        gp.eHandler.checkEvent();
        
        gp.keyH.enterPressed = false;

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

        }

    }

    public void interactNPC(int i) {
        if(i != 999) {
            if(gp.keyH.enterPressed == true) {
            gp.gameState = gp.dialogueState;
            gp.npc[i].speak();
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

        g2.drawImage(image, screenX, screenY, null);

        //PLAYER COLLISION BOX CHECKER - Uncomment to Enable

        //g2.setColor(Color.red);
        //g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
    }
}
