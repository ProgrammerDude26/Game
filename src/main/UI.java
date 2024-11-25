package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import obj.OBJ_Key;

public class UI {

    GamePanel gp;
    Font arial_23, arial_40B;
    BufferedImage keyImage;

    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_23 = new Font("Arial", Font.PLAIN, 23);
        arial_40B = new Font("Arial", Font.BOLD, 40);
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        if(gameFinished == true) {

            g2.setFont(arial_23);
            g2.setColor(Color.lightGray);

            String text;
            int textLength;
            int x;
            int y;
            text = "You Found The Chest";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = 270;
            y = gp.screenHeight/2 + (gp.tileSize*2) + 35;
            g2.drawString(text, x, y);

            text = "Time: " + dFormat.format(playTime);
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = 270;
            y = gp.screenHeight/2 + (gp.tileSize*3);;
            g2.drawString(text, x, y);

            g2.setFont(arial_40B);
            g2.setColor(Color.lightGray);
            text = "Congratulations";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = 240;
            y = gp.screenHeight/2 + (gp.tileSize*2) + 2;
            g2.drawString(text, x, y);
            gp.gameThread = null; // STOPS GAME THREAD
        }
        else{

        g2.setFont(arial_23);
        g2.setColor(Color.lightGray);
        g2.drawImage(keyImage, 670, 510, gp.tileSize - 10, gp.tileSize - 10, null);
        g2.drawString("x "+gp.player.hasKey, 715, 548);

        //TIME
        playTime +=(double)1/60;
        g2.drawString("Time:"+ dFormat.format(playTime), 340, 560);

        //MESSAGE
        if(messageOn == true) {

            g2.setFont(g2.getFont().deriveFont(20));
            g2.drawString(message, 325, 30);
            messageCounter++;
            if(messageCounter > 120) {
                messageCounter = 0;
                messageOn = false;
            }
        }
    }
    }
}
