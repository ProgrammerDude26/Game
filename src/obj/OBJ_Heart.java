package obj;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Heart extends SuperObject {
        GamePanel gp;
        
        public OBJ_Heart(GamePanel gp) {
        
        this.gp = gp;

        name = "Heart";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/healthFull.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/res/objects/healthHalf.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/res/objects/healthEmpty.png"));
            image = uTool.scaledImage(image, gp.tileSize, gp.tileSize);
            image2 = uTool.scaledImage(image2, gp.tileSize, gp.tileSize);
            image3 = uTool.scaledImage(image3, gp.tileSize, gp.tileSize);

        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

}
