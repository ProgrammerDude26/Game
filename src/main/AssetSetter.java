package main;

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

        gp.obj[0] = new OBJ_Key(); // Key 3
        gp.obj[0].worldX = 10 * gp.tileSize;
        gp.obj[0].worldY = 37 * gp.tileSize;

        gp.obj[1] = new OBJ_Key(); // Key 2
        gp.obj[1].worldX = 45 * gp.tileSize; 
        gp.obj[1].worldY = 41 * gp.tileSize;

        gp.obj[2] = new OBJ_Key(); // Key 1
        gp.obj[2].worldX = 26 * gp.tileSize; 
        gp.obj[2].worldY = 21 * gp.tileSize;

        gp.obj[3] = new OBJ_Door(); // Door 3
        gp.obj[3].worldX = 6 * gp.tileSize; 
        gp.obj[3].worldY = 7 * gp.tileSize;

        gp.obj[4] = new OBJ_Door(); // Door 2
        gp.obj[4].worldX = 42 * gp.tileSize; 
        gp.obj[4].worldY = 38 * gp.tileSize;
        
        gp.obj[5] = new OBJ_Door(); // Door 1
        gp.obj[5].worldX = 24 * gp.tileSize; 
        gp.obj[5].worldY = 24 * gp.tileSize;

        gp.obj[6] = new OBJ_Chest(); // Wins The Game
        gp.obj[6].worldX = 6 * gp.tileSize; 
        gp.obj[6].worldY = 3 * gp.tileSize;

        gp.obj[7] = new OBJ_Speed_Potion();
        gp.obj[7].worldX = 44 * gp.tileSize; 
        gp.obj[7].worldY = 16 * gp.tileSize;

         

    }
}
