package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_1 extends Entity{

    public NPC_1(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;
        getImage();
        setDialogue();
}
        public void getImage() {

        up1 = setup("/res/npc/npc1Up1", gp.tileSize, gp.tileSize);
        up2 = setup("/res/npc/npc1Up2", gp.tileSize, gp.tileSize);
        down1 = setup("/res/npc/npc1Down1", gp.tileSize, gp.tileSize);
        down2 = setup("/res/npc/npc1Down2", gp.tileSize, gp.tileSize);
        left1 = setup("/res/npc/npc1Left1", gp.tileSize, gp.tileSize);
        left2 = setup("/res/npc/npc1Left2", gp.tileSize, gp.tileSize);
        right1 = setup("/res/npc/npc1Right1", gp.tileSize, gp.tileSize);
        right2 = setup("/res/npc/npc1Right2", gp.tileSize, gp.tileSize);

    }
    public void setDialogue() {
        dialogues[0] = "Hello...";
        dialogues[1] = "Find The Orb...";
        dialogues[2] = "The World Will Fall Without The Orb\n";
        dialogues[3] = "Go Now!";
    }
    public void setAction() {

        actionLockCounter ++;

        if(actionLockCounter == 120) {

        Random random = new Random();
        int i = random.nextInt(100)+1; // Pick a number from 1 - 100

        if(i <= 25) {
            direction = "up";
        }
        if(i > 25 && i <= 50) {
            direction = "down";
        }
        if(i > 50 && i <= 75) {
            direction = "left";
        }
        if(i > 75 && i <= 100) {
            direction = "right";
        }

        actionLockCounter = 0;
    }
    }
    public void speak() {
        super.speak();
    }
}
