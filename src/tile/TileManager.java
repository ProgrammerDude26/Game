package tile;

import main.GamePanel;
import main.UtilityTool;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import javax.imageio.ImageIO;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {

        this.gp = gp;

        tile = new Tile[50];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/res/maps/world01.txt");
    }

    public void getTileImage() {
        
        // Start Placeholder - starts at  10 for easier map editing & to avoid null pointer
        setup(0, "block", false);
        setup(1, "block", false);
        setup(2, "block", true);
        setup(3, "block", true);
        setup(4, "block", false);
        setup(5, "block", false);
        setup(6, "block", false);
        setup(7, "block", false);
        setup(8, "block", false);
        setup(9, "block", false);
        //  End Placeholder - starts at  10 for easier map editing & to avoid null pointer

        setup(10, "earthHorizontal", false);
        setup(11, "earthVertical", false);
        setup(12, "water", true);
        setup(13, "wall", true);
        setup(14, "sand", false);
        setup(15, "grass", false);
        setup(16, "grass2", false);
        setup(17, "earthDownT", false);
        setup(18, "earthUpT", false);
        setup(19, "earthLeftT", false);
        setup(20, "earthRightT", false);
        setup(21, "earthCross", false);
        setup(22, "earthDownLeftL", false);
        setup(23, "earthDownRightL", false);
        setup(24, "earthUpLeftL", false);
        setup(25, "earthUpRightL", false);
        setup(26, "waterSandL", true);
        setup(27, "waterSandR", true);
        setup(28, "waterSandUp", true);
        setup(29, "waterSandDown", true);
        setup(30, "waterSandTopRightL", true);
        setup(31, "waterSandTopLeftL", true);
        setup(32, "waterSandBottomRightL", true);
        setup(33, "waterSandBottomLeftL", true);
        
    }

    public void setup(int index, String imageName, boolean collision){
        UtilityTool uTool = new UtilityTool();

        try{
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/"+ imageName +".png"));
            tile[index].image = uTool.scaledImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow) {

                String line = br.readLine();

                while(col < gp.maxScreenCol) {

                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;

                }
                if(col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        }
        catch(Exception e) {

        }
    }
    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
               worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
               worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
               worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                    g2.drawImage(tile[tileNum].image, screenX, screenY, null);
                }

            worldCol++;

            if(worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}