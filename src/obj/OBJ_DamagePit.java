package obj;
import entity.Entity;
import main.GamePanel;

public class OBJ_DamagePit extends Entity{
    
    public OBJ_DamagePit(GamePanel gp) {
        super(gp);

        name = "Damage Pit";
        down1 = setup("/res/objects/damagePit");

        solidArea.x = 0;
        solidArea.y = 10;
        solidArea.width = 60;
        solidArea.height = 60;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
