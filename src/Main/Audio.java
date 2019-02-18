package Main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Audio implements Runnable {
    private String fileName;
    private boolean playing = false;
    private Clip clip;
    private volatile boolean isRunning = true;

    public Audio(String fileName){
        this.fileName = fileName;
    }

    @Override
    public void run() {
        while(isRunning) {
            try {
                if(!playing) {
                    clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(Menu.class.getResourceAsStream("/Wavs/" + fileName));
                    clip.open(inputStream);
                    clip.start();
                    playing = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
                isRunning = false;
            }
        }
        clip.stop();
    }

    public void stop(){
        this.isRunning = false;
    }
}
