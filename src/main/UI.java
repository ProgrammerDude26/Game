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

import entity.Entity;
import obj.OBJ_Heart;
import obj.OBJ_Key;

public class UI {

    GamePanel gp;
    Font oldeEnglish, homeVideo_Regular;
    Graphics2D g2;
    BufferedImage healthFull, healthHalf, healthEmpty;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currrentDialogue  = "";
    public int commandNum = 0;
    public int titleScreenState = 0; // 0: FIRST SCREEN 1: SECOND SCREEN


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

        // CREATE HUD OBJECT
        Entity heart = new OBJ_Heart(gp);
        healthFull = heart.image;
        healthHalf = heart.image2;
        healthEmpty = heart.image3;
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
        //TITLE STATE
        if(gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
        // PLAY STATE
        if(gp.gameState == gp.playState) {
            drawPlayerLife();
        }
        // PAUSE STATE
        if(gp.gameState == gp.pauseState) {
            drawPlayerLife();
            drawPauseScreen();
        }
        // DIALOGUE STATE
        if(gp.gameState == gp.dialogueState) {
            drawPlayerLife();
            drawDialogueScreen();
        }
    }
    public void drawPlayerLife() {

        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int i = 0;

        // DRAWS MAX LIFE
        while(i < gp.player.maxLife/2) { // 2 Life = 1 Heart
            g2.drawImage(healthEmpty, x, y, null);
            i++;
            x += gp.tileSize;
        }

        // RESET VALUES
        x = gp.tileSize / 2;
        y = gp.tileSize / 2;
        i = 0;

        // DRAWS CURRENT HEALTH
        while(i < gp.player.life) {
            g2.drawImage(healthHalf, x, y, null);
            i++;
            if(i < gp.player.life) {
                g2.drawImage(healthFull, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }


    }
    public void drawTitleScreen() {

        if(titleScreenState == 0) {

        // TITLE NAME
        g2.setColor(new Color(10,0,0,220));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
        String text = "THE GAME";
        int x = getXforCenteredText(text);
        int y = gp.tileSize * 3;

        // SHADOW
        g2.setColor(new Color(220,220,220,50));
        g2.drawString(text, x + 5, y + 5);

        // MAIN COLOR
        g2.setColor(Color.white) ;
        g2.drawString(text, x, y);

        // MAIN SCREEN PLAYER IMAGE
        x = gp.screenWidth - 250;
        y += gp.tileSize * 3;
        g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

        // MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));
        text = "New Game";
        x = getXforCenteredText(text);
        y += 0;
        g2.drawString(text, x, y);
        if(commandNum == 0) {
            g2.drawString("-", x - gp.tileSize, y); // USE drawImage FOR ICON
        }

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));
        text = "Load Game";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 1) {
            g2.drawString("-", x - gp.tileSize, y); // USE drawImage FOR ICON
        }

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));
        text = "Quit";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 2) {
            g2.drawString("-", x - gp.tileSize, y); // USE drawImage FOR ICON
        }

    }
    else if(titleScreenState == 1) {

        // CLASS SELECTION SCREEN
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(42F));
        String text = "Select Your Origin";
        int x = getXforCenteredText(text);
        int y = gp.tileSize;
        g2.drawString(text,x,y);

        text = "Origin 1";
        x = getXforCenteredText(text);
        y+= gp.tileSize * 3;
        g2.drawString(text,x,y);
        if(commandNum == 0) {
            g2.drawString("-", x-gp.tileSize, y);
        }

        text = "Origin 2";
        x = getXforCenteredText(text);
        y+= gp.tileSize;
        g2.drawString(text,x,y);
        if(commandNum == 1) {
            g2.drawString("-", x-gp.tileSize, y);
        }

        text = "Origin 3";
        x = getXforCenteredText(text);
        y+= gp.tileSize;
        g2.drawString(text,x,y);
        if(commandNum == 2) {
            g2.drawString("-", x-gp.tileSize, y);
        }

        text = "Back";
        x = getXforCenteredText(text);
        y+= gp.tileSize * 2;
        g2.drawString(text,x,y);
        if(commandNum == 3) {
            g2.drawString("-", x-gp.tileSize, y);
        }
        
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
