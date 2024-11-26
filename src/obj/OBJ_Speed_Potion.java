package obj;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

    //GamePanel gp;

public class OBJ_Speed_Potion extends SuperObject {

        public OBJ_Speed_Potion(GamePanel gp) {
        
            //this.gp = gp;

        name = "Speed_Potion";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/item2.png"));
            uTool.scaledImage(image, gp.tileSize, gp.tileSize);

        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

}
