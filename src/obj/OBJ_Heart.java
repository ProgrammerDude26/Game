package obj;
import entity.Entity;
import main.GamePanel;

public class OBJ_Heart extends Entity {
        
        public OBJ_Heart(GamePanel gp) {
            super(gp);

        name = "Heart";
        image = setup("/res/objects/healthFull", gp.tileSize, gp.tileSize);
        image2 = setup("/res/objects/healthHalf", gp.tileSize, gp.tileSize);
        image3 = setup("/res/objects/healthEmpty", gp.tileSize, gp.tileSize);
    }

}
