package main;

import entity.NPC_1;
import obj.OBJ_Chest;
import obj.OBJ_Door;
import obj.OBJ_Key;
import obj.OBJ_Speed_Potion;

public class AssetSetter {

    GamePanel gp;
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }
    public void setObject() {

    }
    public void setNPC() {
        gp.npc[0] = new NPC_1(gp);
        gp.npc[0].worldX = gp.tileSize*30;
        gp.npc[0].worldY = gp.tileSize*18;
    }
}
