package main;

import entity.NPC_1;
import obj.OBJ_Door;

public class AssetSetter {

    GamePanel gp;
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }
    public void setObject() {

        gp.obj[0] = new OBJ_Door(gp);
        gp.obj[0].worldX = gp.tileSize*28;
        gp.obj[0].worldY = gp.tileSize*28;

    }
    public void setNPC() {
        gp.npc[0] = new NPC_1(gp);
        gp.npc[0].worldX = gp.tileSize*30;
        gp.npc[0].worldY = gp.tileSize*18;
    }
}
