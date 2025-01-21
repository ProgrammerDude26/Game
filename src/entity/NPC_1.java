package entity;

import main.GamePanel;

public class NPC_1 extends Entity{

    public NPC_1(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;
        getImage();
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

}
