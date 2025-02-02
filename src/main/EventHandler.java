package main;


public class EventHandler {
    GamePanel gp;
    EventRect eventRect[][];

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;


    public EventHandler(GamePanel gp) {

        this.gp = gp;

        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];
        int col = 0;
        int row = 0;
        while(col < gp.maxWorldCol && row < gp.maxWorldRow) {

        eventRect[col][row] = new EventRect();
        eventRect[col][row].x = 15;
        eventRect[col][row].y = 15;
        eventRect[col][row].width = 10;
        eventRect[col][row].height = 10;
        eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
        eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;
        col++;
        if(col == gp.maxWorldCol) {
            col = 0;
            row++;

            }
        }
    }

    public void checkEvent() {

        // CHECK IF PLAYER IS MORE THAN 1 TILE AWAY FROM LAST EVENT
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance,yDistance);
        if(distance > gp.tileSize) {
            canTouchEvent = true;
        }
        // EVENTS
        if(canTouchEvent = true) {
        if(hit(25,22,"any") == true) {damagePit(25,22,gp.dialogueState);} // DAMAGE PIT EVENT
        if(hit(22,22,"any") == true) {healingPool(22,22,gp.dialogueState);} // HEALING PIT EVENT
        }

    }

    public boolean hit(int col, int row, String reqDireciton) {

        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect[col][row].x = col*gp.tileSize + eventRect[col][row].x;
        eventRect[col][row].y = row*gp.tileSize + eventRect[col][row].y;

        if(gp.player.solidArea.intersects(eventRect[col][row]) && eventRect[col][row].eventDone == false) {
            if(gp.player.direction.contentEquals(reqDireciton) || reqDireciton.contentEquals("any")) {
                hit = true;

                previousEventX = gp.player.worldX;
                previousEventY = gp.player.worldY;
            }
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;

        return hit;

    }
    public void damagePit(int col, int row, int gameState) {
        gp.gameState = gameState;
        gp.ui.currrentDialogue = "-1.5 HP";
        gp.player.life -= 3;
        eventRect[col][row].eventDone = true; // ONE TIME USE DAMAGE PIT

}
    public void healingPool(int col, int row, int gameState) {
        gp.gameState = gameState;
        gp.ui.currrentDialogue = "Life Restored";
        gp.player.life = gp.player.maxLife;
        eventRect[col][row].eventDone = true; // ONE TIME USE HEALING POOL
        
}
}

