package entity;
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

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPalyerImage();
    }
    public void setDefaultValues(){
        x = 100;
        y = 100;
        speed = 4;
        direction = "up";
    }
    public void getPalyerImage(){
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("../assetd/100/up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("../assetd/100/up2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("../assetd/100/down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("../assetd/100/down2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("../assetd/100/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("../assetd/100/left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("../assetd/100/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("../assetd/100/right2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update() {
        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true|| keyH.rightPressed == true){
            if (this.keyH.upPressed) {
                direction = "up";
               this.y -= this.speed;
            }
      
            if (this.keyH.downPressed) {
                direction = "down";
               this.y += this.speed;
            }
      
            if (this.keyH.leftPressed) {
                direction = "left";
               this.x -= this.speed;
            }
      
            if (this.keyH.rightPressed) {
                direction = "right";
               this.x += this.speed;
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
        int scaleFactor = 2; 
        int newWidth = gp.tileSize * scaleFactor;
        int newHeight = gp.tileSize * scaleFactor;

        g2.drawImage(image, x, y, newWidth, newHeight, null);
     }
}
