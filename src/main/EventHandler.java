package main;

import java.awt.Rectangle;

public class EventHandler {
    GamePanel gp;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;


    public EventHandler(GamePanel gp) {
        this.gp = gp;

        eventRect = new Rectangle();
        eventRect.x = 23;
        eventRect.y = 23;
        eventRect.width = 5;
        eventRect.height = 5;
        eventRectDefaultX = eventRect.y;
        eventRectDefaultY = eventRect.y;

    }

    public void checkEvent() {

        if(hit(25,22,"right") == true){ damagePit(gp.dialogueState);} // DAMAGE PIT EVENT
        if(hit(22,22,"left") == true){healingPool(gp.dialogueState);} // HEALING PIT EVENT

    }

    public boolean hit(int eventCol, int eventRow, String reqDireciton) {

        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect.x = eventCol*gp.tileSize + eventRect.x;
        eventRect.y = eventRow*gp.tileSize + eventRect.y;

        if(gp.player.solidArea.intersects(eventRect)) {
            if(gp.player.direction.contentEquals(reqDireciton) || reqDireciton.contentEquals("any")) {
                hit = true;
            }
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;

        return hit;

    }
    public void damagePit(int gameState) {
        gp.gameState = gameState;
        gp.ui.currrentDialogue = "-0.5 HP";
        gp.player.life -= 1;

}
    public void healingPool(int gameState) {
            gp.gameState = gameState;
            gp.ui.currrentDialogue = "Life Restored";
            gp.player.life = gp.player.maxLife;

}
}
