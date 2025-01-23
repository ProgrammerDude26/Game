package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import obj.OBJ_Key;

public class UI {

    GamePanel gp;
    Font arial_23, arial_40B;
    Graphics2D g2;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currrentDialogue  = "";


    public UI(GamePanel gp) {
        this.gp = gp;
        arial_23 = new Font("Arial", Font.PLAIN, 23);
        arial_40B = new Font("Arial", Font.BOLD, 40);
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {

        this.g2 = g2;

        g2.setFont(arial_23);
        g2.setColor(Color.LIGHT_GRAY);
        // PLAY STATE
        if(gp.gameState == gp.playState) {
            // DOing Later
        }
        // PAUSE STATE
        if(gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }
        // DIALOGUE STATE
        if(gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
        }
    }
    public void drawPauseScreen() {

        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);
    }
    public void drawDialogueScreen() {

        // WINDOW for DIALOGUE
        int x = gp.tileSize * 2;
        int y = gp.tileSize * 6;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 2;
        drawSubWindow(x, y, width, height);

        Color c = new Color(255,255,255);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,28));
        g2.setColor(c);
        x += gp.tileSize;
        y += gp.tileSize;
        g2.drawString(currrentDialogue, x, y);
    }

    public void drawSubWindow(int x, int y, int width, int height) {
        // DIALOGUE BOX
        Color c = new Color(41,0,0, 215);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 32, 32);
        // DIALOGUE BOX INNER
        c = new Color(2, 2,2);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(4));
        g2.drawRoundRect(x+7, y+7, width-11, height-11, 23, 23);
    }

    public int getXforCenteredText(String text) {

        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
}
