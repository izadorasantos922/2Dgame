package objects;

import javax.imageio.ImageIO;

public class ObjKey extends SuperObject{
    public ObjKey(){
        name = "Key";
        try {
            image = ImageIO.read(getClass().getResource("../assetd/objects/key.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
