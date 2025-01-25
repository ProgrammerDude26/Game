package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import obj.OBJ_Key;

public class UI {

    GamePanel gp;
    Font oldeEnglish, homeVideo_Regular;
    Graphics2D g2;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currrentDialogue  = "";


    public UI(GamePanel gp) {
        this.gp = gp;
        try {
            InputStream is = getClass().getResourceAsStream("/res/font/OldeEnglish.ttf");
        oldeEnglish = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/res/font/HomeVideo-Regular.ttf");
            homeVideo_Regular = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {

        this.g2 = g2;

        g2.setFont(oldeEnglish);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
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
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,30));
        g2.setColor(c);
        x += gp.tileSize;
        y += gp.tileSize;

        //SPLITS TEXT FOR NEW LINE IN BOX
        for(String line : currrentDialogue.split("\n")) {
            g2.drawString(line, x, y);
                y += 40;
        }
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
