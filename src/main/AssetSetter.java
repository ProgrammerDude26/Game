package main;

import entity.NPC_1;
import obj.OBJ_DamagePit;
import obj.OBJ_HealingPool;

public class AssetSetter {

    GamePanel gp;
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }
    public void setObject() {

        gp.obj[0] = new OBJ_HealingPool(gp);
        gp.obj[0].worldX = gp.tileSize*22;
        gp.obj[0].worldY = gp.tileSize*22;

        gp.obj[1] = new OBJ_DamagePit(gp);
        gp.obj[1].worldX = gp.tileSize*25;
        gp.obj[1].worldY = gp.tileSize*22;

    }
    public void setNPC() {
        
        gp.npc[0] = new NPC_1(gp);
        gp.npc[0].worldX = gp.tileSize*30;
        gp.npc[0].worldY = gp.tileSize*18;
    }
}
