package entity;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;

import java.awt.Color;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    int hasKey = 0;
    // public boolean collisionOn = false;
    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        screenX = gp.screenWidth/2;
        screenY = gp.screenHeight/2;
        solidArea = new Rectangle(25, 45, 30, 30); 
        solidArea.x = 28;
        solidArea.y = 45;
        solidArea.width = 25;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea = new Rectangle(solidArea.x,solidArea.y,solidArea.width,solidArea.height);
        setDefaultValues();
        getPalyerImage();

    }
    public void setDefaultValues(){
        worldX = gp.tileSize * 23 - (gp.tileSize/2);
        worldY = gp.tileSize * 21- (gp.tileSize/2);
        speed = 4;
        direction = "up";
    }
    public void getPalyerImage(){
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("../assetd/player/up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("../assetd/player/up2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("../assetd/player/down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("../assetd/player/down2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("../assetd/player/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("../assetd/player/left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("../assetd/player/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("../assetd/player/right2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update() {
        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true|| keyH.rightPressed == true){
            if (this.keyH.upPressed) {
                direction = "up";
               
            }
      
            if (this.keyH.downPressed) {
                direction = "down";
               
            }
      
            if (this.keyH.leftPressed) {
                direction = "left";
            }
      
            if (this.keyH.rightPressed) {
                direction = "right";
               
            }
            this.collisionOn = false;
            gp.cChecker.checkTile(this);
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUp(objIndex);
            if(this.collisionOn == false){
                switch (direction) {
                    case "up":
                        this.worldY -= this.speed;
                        break;
                    case "left":
                    this.worldX -= this.speed;
                        break;
                    case "right":
                        this.worldX += this.speed;
                        break;
                    case "down":
                        this.worldY += this.speed;
                        break;
                
                    default:
                        break;
                }
            }
            spriteCOunter++;
            if(spriteCOunter > 12){
                if(spriteNum == 1){
                    spriteNum = 2;
                }else if(spriteNum == 2){
                    spriteNum =1;
                }
                spriteCOunter = 0;
            }

        }
        
     }
     public void pickUp(int i){
        if(i != 999){
            String objectName = gp.obj[i].name;
            switch (objectName) {
                case "Key":
                    hasKey++;
                    gp.obj[i] = null;
                    break;
                case "Door":
                    if(hasKey > 0){
                        gp.obj[i] = null;
                        hasKey--;
                        
                    }
                    break;
                case "Cake":
                    speed += 2;
                    gp.obj[i] = null;
                    System.out.println("cake ckae");
                    break;
                default:
                    break;
            }
        }

     }
     public void draw(Graphics2D g2){
        // g2.setColor(Color.white);
        // g2.fillRect(x,y, gp.tileSize, gp.tileSize);
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if(spriteNum == 1){
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
            if(spriteNum == 1){
                image = down1;
            }
            if(spriteNum == 2){
                image = down2;
            }
                break;
            case "left":
                if(spriteNum == 1){
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                break;
            case "right":
            if(spriteNum == 1){
                image = right1;
            }
            if(spriteNum == 2){
                image = right2;
            }
                break;
            default:
                break;
        }
        float scaleFactor = 1.5f; 
        int newWidth = (int)(gp.tileSize * scaleFactor);
        int newHeight = (int)(gp.tileSize * scaleFactor);        


        g2.drawImage(image, screenX, screenY, newWidth, newHeight, null);
     }
}
