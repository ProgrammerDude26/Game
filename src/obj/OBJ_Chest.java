package obj;
import entity.Entity;
import main.GamePanel;

    // GamePanel gp;

public class OBJ_Chest extends Entity {

        public OBJ_Chest(GamePanel gp) {
            super(gp);

        name = "Chest";
        down1 = setup("/res/objects/chest");
    }

}
