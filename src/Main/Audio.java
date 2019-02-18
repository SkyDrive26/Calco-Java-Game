package Main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Audio {
    private String fileName;
    private boolean isRunning = true;

    public Audio(String fileName){
        this.fileName = fileName;
    }

    public synchronized void playSound(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(Menu.class.getResourceAsStream("/Wavs/" + fileName));
                    clip.open(inputStream);
                    clip.start();
                    while(!isRunning){
                        clip.stop();
                        break;
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void stop(){
        this.isRunning = false;
    }
}
