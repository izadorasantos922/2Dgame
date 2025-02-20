package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    Clip clip;
    URL soundUrl[] = new URL[30];
    public Sound(){
        soundUrl[0] = getClass().getResource("../musicgame/musicgame.wav");
        
        soundUrl[1] = getClass().getResource("../musicgame/bing.wav");
        soundUrl[2] = getClass().getResource("../musicgame/gametreasure.wav");
        soundUrl[3] = getClass().getResource("../musicgame/punch.wav");
        soundUrl[4] = getClass().getResource("../musicgame/death.wav");
        soundUrl[5] = getClass().getResource("../musicgame/opendoor.wav");
        soundUrl[6] = getClass().getResource("../musicgame/yahoo.wav");
    }
    public void setFile(int i){
        try {
            if (soundUrl[i] == null) {
                System.out.println("Arquivo de som não encontrado: " + i);
                return;
            }
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundUrl[i]);
            clip = AudioSystem.getClip();
            if (clip == null) {
                System.out.println("Falha ao criar o clip");
                return;
            }
            clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();  // Mostrar o erro detalhado
        }
    }
    
    
    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);

    }
    public void stop(){
        clip.stop();
    }
}
