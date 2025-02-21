package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import entity.Player;
import objects.SuperObject;
import tile.TileManager;
public class GamePanel  extends JPanel implements Runnable{
    public final int originalTileSize = 16;
    public final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    public final int maxWorlCol = 50;
    public final int maxWorlRow = 50;
    public final int worldWidth = tileSize * maxScreenCol;
    public final int worldHeight = tileSize * maxScreenRow;
    public UI ui = new UI(this);
    Sound music = new Sound();
    Sound soundEffect = new Sound();
    int FPS = 60;
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public SuperObject obj[] = new SuperObject[10];
    public AssetSetter aSetter = new AssetSetter(this);
    public CheckCollision cChecker = new CheckCollision(this);
    public Player player = new Player(this,keyH);

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH); 
        this.setFocusable(true);
    }
    public void setupGame(){
        aSetter.setObject();
        playMusic(0);
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    // first method
    // @Override
    // public void run(){
    //     double drawInterval = 1_000_000_000.0 / FPS;
    //     double nextDrawTime = System.nanoTime() + drawInterval;
    //     while (gameThread != null) {
    //         update();
    //         repaint();

    //         double remainingTime = nextDrawTime - System.nanoTime();
    //         remainingTime = remainingTime/1000000;
    //         if(remainingTime < 0){
    //             remainingTime = 0;
    //         }
    //         try {
    //             Thread.sleep((long) remainingTime);
    //             nextDrawTime += drawInterval;
    //         } catch (InterruptedException e) {
    //             e.printStackTrace();
                
    //         }
    //     }
    // }
    // second method
    public void run(){
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTIme;
        long timer = 0;
        int drawCount = 0;
        while(gameThread != null){
            currentTIme = System.nanoTime();
            delta += (currentTIme - lastTime) / drawInterval;
            timer += (currentTIme - lastTime);
            lastTime = currentTIme;
            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if(timer >= 1000000000){
                // System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }

    }
    public void update() {
        player.update();
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        tileM.draw(g2);
        for(int i = 0; i < obj.length; i++){
            if(obj[i] != null){
                obj[i].draw(g2, this);
            }
        }
        player.draw(g2);
        ui.draw(g2);
        g2.dispose();
    }
    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic(){
        music.stop();
    }
    public void playSE(int i){
        soundEffect.setFile(i);
        soundEffect.play();
    }
}
