package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class MON_Monster_1 extends Entity {

    public MON_Monster_1(GamePanel gp) {
        super(gp);
        name = "Monster 1";
        type = 2;
        speed = 1;
        maxLife = 3;
        life = maxLife;
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
        
    }
    public void getImage() {
        up1 = setup("/res/monster/monster1_1");
        up2 = setup("/res/monster/monster1_2");
        down1 = setup("/res/monster/monster1_1");
        down2 = setup("/res/monster/monster1_2");
        left1 = setup("/res/monster/monster1_1");
        left2 = setup("/res/monster/monster1_2");
        right1 = setup("/res/monster/monster1_1");
        right2 = setup("/res/monster/monster1_2");
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

}
