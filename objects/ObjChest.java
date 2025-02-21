package objects;

import javax.imageio.ImageIO;

public class ObjChest extends SuperObject{
     public ObjChest(){
        name = "Chest";
        try {
            image = ImageIO.read(getClass().getResource("../assetd/objects/chest.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;

    }
}
