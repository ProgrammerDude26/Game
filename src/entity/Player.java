package entity;
import java.awt.AlphaComposite;
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

        attackArea.width = 36;
        attackArea.height = 36;

        setDefaultValues();
        getPlayerImage();
        getPlayerAtkImage();
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

        up1 = setup("/res/player/PlayerUp1", gp.tileSize, gp.tileSize);
        up2 = setup("/res/player/PlayerUp2", gp.tileSize, gp.tileSize);
        down1 = setup("/res/player/PlayerDown1", gp.tileSize, gp.tileSize);
        down2 = setup("/res/player/PlayerDown2", gp.tileSize, gp.tileSize);
        left1 = setup("/res/player/PlayerLeft1", gp.tileSize, gp.tileSize);
        left2 = setup("/res/player/PlayerLeft2", gp.tileSize, gp.tileSize);
        right1 = setup("/res/player/PlayerRight1", gp.tileSize, gp.tileSize);
        right2 = setup("/res/player/PlayerRight2", gp.tileSize, gp.tileSize);

    }

    public void getPlayerAtkImage() {
        attackUp1 = setup("/res/player/PlayerAttackUp1", gp.tileSize, gp.tileSize * 2);
        attackUp2 = setup("/res/player/PlayerAttackUp2", gp.tileSize, gp.tileSize * 2);
        attackDown1 = setup("/res/player/PlayerAttackDown1", gp.tileSize, gp.tileSize * 2);
        attackDown2 = setup("/res/player/PlayerAttackDown2", gp.tileSize, gp.tileSize * 2);
        attackLeft1 = setup("/res/player/PlayerAttackLeft1", gp.tileSize * 2, gp.tileSize);
        attackLeft2 = setup("/res/player/PlayerAttackLeft2", gp.tileSize * 2, gp.tileSize);
        attackRight1 = setup("/res/player/PlayerAttackRight1", gp.tileSize * 2, gp.tileSize);
        attackRight2 = setup("/res/player/PlayerAttackRight2", gp.tileSize * 2, gp.tileSize);
    }

    public void update() {
        // If the player is attacking but the attack key was released,
        // cancel the attack so it doesn't continue indefinitely.
        if(attacking == true && gp.keyH.spacePressed == false) {
            attacking = false;
            spriteCounter = 0;
        }

        if(attacking == true){
            attacking();
        }
        
        else if(keyH.upPressed == true || 
           keyH.downPressed == true || 
           keyH.leftPressed == true || 
           keyH.rightPressed == true || 
           keyH.enterPressed == true) { 
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

        // CHECK MONSTER COLLISION
        int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
        contactMonster(monsterIndex);

        // CHECK EVENT
        gp.eHandler.checkEvent();
        
        

        // IF COLLISION IS FALSE, PLAYER CAN MOVE
        if(collisionOn == false & keyH.enterPressed == false) {

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

        gp.keyH.enterPressed = false;

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
        if(invincible == true){
            invincibleCounter ++;
            if(invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void attacking(){

        spriteCounter++;

        if(spriteCounter <= 5){
            spriteNum = 1;
        }
        if(spriteCounter > 5 && spriteCounter <= 25){
            spriteNum = 2;
            
            // Save Current World x, y, solidArea
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            // Adjust World x,y, for attack area
            switch(direction){
                case "up": worldY -= attackArea.height;
                    break;
                case "down": worldY += attackArea.height;
                    break;
                case "left": worldX -= attackArea.width;
                    break;
                case "right": worldX += attackArea.width;
                    break;
            }

            // attackArea becomes solidArea
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            // Check Monster collision with the updated world x,y.solidArea
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex);

            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;

        }
        if(spriteCounter > 25){
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
        
    }

    public void pickUpObject(int i) {
        if(i != 999) {

        }
    }

    public void interactNPC(int i) {

        if(i != 999) {
            if(gp.keyH.enterPressed == true){
            gp.gameState = gp.dialogueState;
            gp.npc[i].speak();
            }
        }
        else {
            if(gp.keyH.spacePressed == true){
            attacking = true;
            }
        }
    }
    
    
    public void contactMonster(int i) {

        if(i != 999) {
            if(invincible == false) {
                life -= 1;
                invincible = true;
            }
        }
    }

    public void damageMonster(int i){
        if(i != 999){
            if(gp.monster[i].invincible == false){

                gp.monster[i].life -= 1;
                gp.monster[i].invincible = true;

                if(gp.monster[i].life <= 0){
                    gp.monster[i] = null;
                }
            }
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        int drawX = screenX;
        int drawY = screenY;

        switch(direction) {
        case "up":
            if(attacking == false){
                if(spriteNum == 1) {image = up1;}
                if(spriteNum == 2) {image = up2;}
            }
            if(attacking == true){
                drawY = screenY - gp.tileSize; // Offset up for larger sprite
                if(spriteNum == 1) {image = attackUp1;}
                if(spriteNum == 2) {image = attackUp2;}   
            }
            break;
        case "down":
            if(attacking == false){
                if(spriteNum == 1) {image = down1;}
                if(spriteNum == 2) {image = down2;}
            }
            if(attacking == true){
                if(spriteNum == 1) {image = attackDown1;}
                if(spriteNum == 2) {image = attackDown2;}
            }
            break;
        case "left":
            if(attacking == false){
                if(spriteNum == 1) {image = left1;}
                if(spriteNum == 2) {image = left2;}
            }
            if(attacking == true){
                drawX = screenX - gp.tileSize; // Offset left for wider sprite
                if(spriteNum == 1) {image = attackLeft1;}
                if(spriteNum == 2) {image = attackLeft2;}
            }
            break;
        case "right":
            if(attacking == false){
                if(spriteNum == 1) {image = right1;}
                if(spriteNum == 2) {image = right2;}
            }
            if(attacking == true){
                if(spriteNum == 1) {image = attackRight1;}
                if(spriteNum == 2) {image = attackRight2;}
            }
            break;
        }

        if(invincible == true) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }

        g2.drawImage(image, drawX, drawY, null);

        //RESET ALPHA
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));



        
        //PLAYER COLLISION BOX CHECKER - Uncomment to Enable
        //g2.setColor(Color.red);
        //g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);

        //DEBUG
        //g2.setFont(new Font("Arial", Font.PLAIN, 26));
        //g2.setColor(Color.white);
        //g2.drawString("Invincible:"+invincibleCounter, 10, 400);
    }
}
