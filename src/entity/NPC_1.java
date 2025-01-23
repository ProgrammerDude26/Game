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

        up1 = setup("/res/npc/npc1Up1");
        up2 = setup("/res/npc/npc1Up2");
        down1 = setup("/res/npc/npc1Down1");
        down2 = setup("/res/npc/npc1Down2");
        left1 = setup("/res/npc/npc1Left1");
        left2 = setup("/res/npc/npc1Left2");
        right1 = setup("/res/npc/npc1Right1");
        right2 = setup("/res/npc/npc1Right2");

    }
    public void setDialogue() {
        dialogues[0] = "Hello...";
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
        gp.ui.currrentDialogue = dialogues[0];
    }
}
