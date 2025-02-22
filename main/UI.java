package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import objects.ObjKey;

public class UI {
    GamePanel gp;
    Font arial_40, arial_80;
    BufferedImage keyImage;
    int messageCounter = 0;
    public boolean messageOn = false;
    public String message = "";
    public boolean gameFinished = false;
    double playtime;
    DecimalFormat dFormat = new DecimalFormat("0.00");
    public UI(GamePanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80 = new Font("Arial", Font.BOLD, 80);
        ObjKey key =  new ObjKey();
        keyImage = key.image;
    }
    public void showMessage(String text){
        message = text;
        messageOn = true;
    }
    public void draw(Graphics2D g2){
        if(gameFinished){
            String text;
            int textLength;
            int x;
            int y;
            x = gp.screenWidth/2;
            y = gp.screenHeight/2;
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            text = ("Você encontrou o Tesouro");
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 - (gp.tileSize*4);
            g2.drawString(text, x,y);

            g2.setFont(arial_80);
            g2.setColor(Color.yellow);
            text = ("Parabéns");
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 - (gp.tileSize*2);
            g2.drawString(text, x,y);
            gp.gameThread = null;

            g2.setFont(arial_40);
            g2.setColor(Color.white);
            text = ("Seu tempo foi: "+ dFormat.format(playtime) + "!");
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 - (gp.tileSize);
            g2.drawString(text, x,y);
        }else{
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
            g2.drawString("x " + gp.player.hasKey, 74, 65);
            playtime += (double)1/60;
            g2.drawString("Time: "+ dFormat.format(playtime), gp.tileSize*11, 65);
            if(messageOn == true){
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.tileSize/2, gp.tileSize*5);
                messageCounter++;
            }
            if(messageCounter > 120){
                messageCounter = 0;
                messageOn = false;
            }
        }
        
    }
}
