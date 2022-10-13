package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    Clip clip;
    URL[] soundURL =new URL[30];

    public Sound(){
        soundURL[0]=getClass().getResource("/sound/soundtrack.wav") ;
        soundURL[1]=getClass().getResource("/sound/place_bomb1.wav") ;
        soundURL[2]=getClass().getResource("/sound/player_dead.wav") ;
        soundURL[3]=getClass().getResource("/sound/pick_object.wav") ;
        soundURL[4]=getClass().getResource("/sound/endgame3.wav") ;
        soundURL[5]=getClass().getResource("/sound/bomb_explode.wav");
    }

    public void setFile(int i) {
        try{
            AudioInputStream aInput= AudioSystem.getAudioInputStream(soundURL[i]);
            clip=AudioSystem.getClip();
            clip.open(aInput);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void play() {
        clip.start();
    }
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop() {
        clip.stop();
    }
}
