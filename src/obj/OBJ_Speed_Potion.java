package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Speed_Potion extends Entity {

        public OBJ_Speed_Potion(GamePanel gp) {
        super(gp);

        name = "Speed_Potion";
        down1 = setup("/res/objects/item2", gp.tileSize, gp.tileSize);
    }

}
