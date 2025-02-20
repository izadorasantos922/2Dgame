package objects;

import java.awt.Graphics2D;

import javax.imageio.ImageIO;

import main.GamePanel;

public class ObjCake extends SuperObject{
    public ObjCake(){
        name = "Cake";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("../assetd/birthday/cake.png"));
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}


