package objects;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class ObjDoor extends SuperObject{
    public ObjDoor(){
        name = "Door";
        try {
            image = ImageIO.read(getClass().getResource("../assetd/objects/door.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
    
}
