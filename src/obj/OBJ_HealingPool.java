package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_HealingPool extends Entity{
    
    public OBJ_HealingPool(GamePanel gp) {
        super(gp);

        name = "Healing Pool";
        down1 = setup("/res/objects/healingPool");

        collision = true;

        solidArea.x = 24;
        solidArea.y = 36;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 12;
        solidArea.height = 12;
    }
}

