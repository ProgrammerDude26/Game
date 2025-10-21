package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_HealingPool extends Entity{
    
    public OBJ_HealingPool(GamePanel gp) {
        super(gp);

        name = "Healing Pool";
        down1 = setup("/res/objects/healingPool", gp.tileSize, gp.tileSize);

    }
}

