package obj;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

    //GamePanel gp;

public class OBJ_Key extends SuperObject {
    public OBJ_Key(GamePanel gp) {

        //this.gp = gp;

        name = "Key";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/item1.png"));
            uTool.scaledImage(image, gp.tileSize, gp.tileSize);

        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}
