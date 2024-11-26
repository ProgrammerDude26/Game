package obj;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

    //GamePanel gp;

public class OBJ_Door extends SuperObject{
    
    public OBJ_Door(GamePanel gp) {
        //this.gp = gp;

        name = "Door";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/door.png"));
            uTool.scaledImage(image, gp.tileSize, gp.tileSize);

        }
        catch(IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
