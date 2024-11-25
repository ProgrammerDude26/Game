package obj;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Speed_Potion extends SuperObject {

        public OBJ_Speed_Potion() {

        name = "Speed_Potion";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/item2.png"));

        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

}
