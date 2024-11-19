package main;

import obj.OBJ_Chest;
import obj.OBJ_Door;
import obj.OBJ_Key;

public class AssetSetter {

    GamePanel gp;
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }
    public void setObject() {

        gp.obj[0] = new OBJ_Key();
        gp.obj[0].worldX = 21 * gp.tileSize;
        gp.obj[0].worldY = 11 * gp.tileSize;

        gp.obj[1] = new OBJ_Key();
        gp.obj[1].worldX = 20 * gp.tileSize; 
        gp.obj[1].worldY = 10 * gp.tileSize;

        gp.obj[2] = new OBJ_Key();
        gp.obj[2].worldX = 24 * gp.tileSize; 
        gp.obj[2].worldY = 5 * gp.tileSize;

        gp.obj[3] = new OBJ_Door();
        gp.obj[3].worldX = 6 * gp.tileSize; 
        gp.obj[3].worldY = 7 * gp.tileSize;

        gp.obj[4] = new OBJ_Door();
        gp.obj[4].worldX = 9 * gp.tileSize; 
        gp.obj[4].worldY = 19 * gp.tileSize;
        
        gp.obj[5] = new OBJ_Door();
        gp.obj[5].worldX = 15 * gp.tileSize; 
        gp.obj[5].worldY = 19 * gp.tileSize;

        gp.obj[6] = new OBJ_Chest();
        gp.obj[6].worldX = 17 * gp.tileSize; 
        gp.obj[6].worldY = 21 * gp.tileSize;
        
         

    }
}
