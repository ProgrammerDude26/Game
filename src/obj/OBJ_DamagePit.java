package obj;
import entity.Entity;
import main.GamePanel;

public class OBJ_DamagePit extends Entity{
    
    public OBJ_DamagePit(GamePanel gp) {
        super(gp);

        name = "Damage Pit";
        down1 = setup("/res/objects/damagePit", gp.tileSize, gp.tileSize);
        collision = true;

        solidArea.x = 26;
        solidArea.y = 36;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 12;
        solidArea.height = 24;

    }
}
